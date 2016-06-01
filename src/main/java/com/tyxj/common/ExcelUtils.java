package com.tyxj.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.biff.EmptyCell;
import jxl.format.Alignment;
import jxl.format.CellFormat;
import jxl.format.VerticalAlignment;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCell;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.tyxj.common.exception.UserInputException;

/**
 * JXL操作Excel公共类
 * 
 * 1、导出excel：数据为：list-ObjectBean，每个list为一行，ObjectBean需有get方法
 * 2、读取excel：读入JVM的数据为：list<Object>；实体bean需有set方法，且属性顺序与excel中行一致
 * 
 * @ClassName: ExcelUtils 
 * @Description: TODO
 * @author xiehz
 * @date 2014年7月4日 上午11:09:23 
 *
 */
public class ExcelUtils {

	private static final Logger LOGGER = Logger.getLogger(ExcelUtils.class);
	
	private static final String EXCEL_DATE_FORMAT = "EXCEL_DATE_FORMAT";
	private static final String EXCEL_TITLE_FORMAT = "EXCEL_TITLE_FORMAT";

	private WritableWorkbook workbook; // 用来创建excel文件
	private WritableSheet sheet; // 工作表
	@SuppressWarnings("unchecked")
	private Map format; // 存放显示风格

	@SuppressWarnings("unchecked")
	public ExcelUtils() {
		format = new HashMap();
		format.put(EXCEL_DATE_FORMAT, "yyyy-MM-dd hh:mm:ss"); // 时间显示格式
		format.put(CellType.BOOLEAN, this.getDataCellFormat(CellType.BOOLEAN));
		format.put(CellType.NUMBER, this.getDataCellFormat(CellType.NUMBER));
		format.put(CellType.DATE, this.getDataCellFormat(CellType.DATE));
		format.put(CellType.STRING_FORMULA, this.getDataCellFormat(CellType.STRING_FORMULA));
		format.put(EXCEL_TITLE_FORMAT, this.getTitleCellFormat());
	}

	/**
	 * 初始化表格属性
	 * 
	 * @param sheet
	 */
	public void initialSheetSetting(WritableSheet sheet) {
		try {
			sheet.getSettings().setDefaultColumnWidth(15); // 设置列的默认宽度
		} catch (Exception e) {
			LOGGER.error("error:"+e.getMessage(), e);
		}
	}

	/**
	 * 插入公式
	 * 
	 * @param sheet
	 * @param col
	 * @param row
	 * @param formula
	 * @param format
	 */
	public void insertFormula(WritableSheet sheet, Integer col, Integer row, String formula, WritableCellFormat format) {
		try {
			Formula f = new Formula(col, row, formula, format);
			sheet.addCell(f);
		} catch (Exception e) {
			LOGGER.error("error:"+e.getMessage(), e);
		}
	}

	/**
	 * 插入一行数据
	 * 
	 * @param sheet
	 *            工作表
	 * @param row
	 *            行号
	 * @param data
	 *            内容 内容类型必须为String
	 * @param format
	 *            风格
	 */
	public void insertRowData(WritableSheet sheet, Integer row, List<String> data, WritableCellFormat format) {
		try {
			Label label;
			for (int i = 0; i < data.size(); i++) {
				label = new Label(i, row, (String) data.get(i), format);
				sheet.addCell(label);
			}
		} catch (Exception e) {
			LOGGER.error("error:"+e.getMessage(), e);
		}
	}

	/**
	 * 插入一行数据
	 * 
	 * @param sheet
	 *            工作表
	 * @param row
	 *            行号
	 * @param data
	 *            内容
	 */
	public void insertRowData(WritableSheet sheet, Integer row, Object data) {
		try {
			// 取实体的属性
			Field fields[] = data.getClass().getDeclaredFields();
			if (fields != null && fields.length > 0) {
				for (int i = 0; i < fields.length; i++) {
					// 取属性的具体值
					Object o = CommonUtil.getPropertyValue(data, fields[i].getName());
					// 取属性的类型
					String typeName = fields[i].getType().getSimpleName();
					WritableCell cell = this.getWritableCell(i, row, o, typeName);
					// 添加到表格上
					sheet.addCell(cell);
				}
			}
		} catch (Exception e) {
			LOGGER.error("error:"+e.getMessage(), e);
		}
	}

	/**
	 * 根据不同的属性类型，返回相应的WritableCell
	 * 
	 * @param col
	 *            列号
	 * @param row
	 *            行号
	 * @param data
	 *            内容
	 * @param typeName
	 *            类型
	 * @return 与类型一致的cell
	 */
	private WritableCell getWritableCell(Integer col, Integer row, Object data, String typeName) {
		WritableCell result = null;
		if (data != null) {
			if ("int".equals(typeName) || "Interger".equals(typeName) || "double".equals(typeName) || "Double".equals(typeName) || "long".equals(typeName)
					|| "Long".equals(typeName) || "float".equals(typeName) || "Float".equals(typeName)) {
				result = new Number(col, row, (Integer) data, (CellFormat) format.get(CellType.NUMBER));
			} else if ("boolean".equals(typeName) || "Boolean".equals(typeName)) {
				result = new jxl.write.Boolean(col, row, (Boolean) data, (CellFormat) format.get(CellType.BOOLEAN));
			} else if ("Date".equals(typeName)) {
				result = new jxl.write.DateTime(col, row, (Date) data, (CellFormat) format.get(CellType.DATE));
			} else {
				result = new Label(col, row, data.toString(), (CellFormat) format.get(CellType.STRING_FORMULA));
			}
		} else {
			result = new EmptyCell(col, row);
		}
		return result;
	}

	/**
	 * 插入单元格数据
	 * 
	 * @param sheet
	 * @param col
	 * @param row
	 * @param data
	 */
	public void insertOneCellData(WritableSheet sheet, Integer col, Integer row, Object data, WritableCellFormat format) {
		try {
			if (data instanceof Double) {
				jxl.write.Number labelNF = new jxl.write.Number(col, row, (Double) data, format);
				sheet.addCell(labelNF);
			} else if (data instanceof java.lang.Boolean) {

				jxl.write.Boolean labelB = new jxl.write.Boolean(col, row, (java.lang.Boolean) data, format);
				sheet.addCell(labelB);
			} else if (data instanceof Date) {
				jxl.write.DateTime labelDT = new jxl.write.DateTime(col, row, (Date) data, format);
				sheet.addCell(labelDT);
				setCellComments(labelDT, "这是个创建表的日期说明！"); // 给单元格加注释(公共方法)
			} else {
				Label label = new Label(col, row, data.toString(), format);
				sheet.addCell(label);
			}
		} catch (Exception e) {
			LOGGER.error("error:"+e.getMessage(), e);
		}
	}

	/**
	 * 合并单元格，并插入数据
	 * 
	 * @param sheet
	 * @param colStart
	 * @param rowStart
	 * @param colEnd
	 * @param rowEnd
	 * @param data
	 * @param format
	 */
	public void mergeCellsAndInsertData(WritableSheet sheet, Integer colStart, Integer rowStart, Integer colEnd, Integer rowEnd, Object data,
			WritableCellFormat format) {
		try {
			sheet.mergeCells(colStart, rowStart, colEnd, rowEnd);// 左上角到右下角
			insertOneCellData(sheet, colStart, rowStart, data, format);
		} catch (Exception e) {
			LOGGER.error("error:"+e.getMessage(), e);
		}
	}

	/**
	 * 给单元格加注释
	 * 
	 * @param label
	 *            对象
	 * @param comments
	 *            注释内容
	 */
	public void setCellComments(Object label, String comments) {
		WritableCellFeatures cellFeatures = new WritableCellFeatures();
		cellFeatures.setComment(comments);
		if (label instanceof jxl.write.Number) {
			jxl.write.Number num = (jxl.write.Number) label;
			num.setCellFeatures(cellFeatures);
		} else if (label instanceof jxl.write.Boolean) {
			jxl.write.Boolean bool = (jxl.write.Boolean) label;
			bool.setCellFeatures(cellFeatures);
		} else if (label instanceof jxl.write.DateTime) {
			jxl.write.DateTime dt = (jxl.write.DateTime) label;
			dt.setCellFeatures(cellFeatures);
		} else {
			Label nLabel = (Label) label;
			nLabel.setCellFeatures(cellFeatures);
		}
	}

	/**
	 * 读取Excel文件，把数据放到list中
	 * 
	 * @param inputFile
	 *            要读取的excel文件
	 * @param inputFileSheetIndex
	 *            读第几个工作表
	 * @param rowNum
	 *            从第几行数据开始
	 * @param bean
	 *            以那个Bean读取
	 * @return 返回一个List，list中放Class对象
	 */
	@SuppressWarnings("unchecked")
	public List<Object> readDataFromExcel(InputStream inputFile, int inputFileSheetIndex, int rowNum, Class bean) {

		List<Object> list = new ArrayList<Object>();
		Workbook book = null;
		Cell cell = null;
		WorkbookSettings setting = new WorkbookSettings();
		try {
			book = Workbook.getWorkbook(inputFile, setting);
		} catch (Exception e) {
			LOGGER.error("error:"+e.getMessage(), e);
		}

		try {
			Object tmpObject = null;
			Sheet readSheet = book.getSheet(inputFileSheetIndex);
			for (int rowIndex = rowNum; rowIndex < readSheet.getRows(); rowIndex++) {// 行
				// 实例一个bean
				tmpObject = bean.newInstance();
				// bean的属性，注意：bean中的属性顺序应该与表格中的属性一致。
				Field[] fields = bean.getDeclaredFields();
				for (int colIndex = 0; colIndex < readSheet.getColumns(); colIndex++) {// 列
					cell = readSheet.getCell(colIndex, rowIndex);
					// 给tmpObject设置属性值
					CommonUtil.setProperty(tmpObject, fields[colIndex].getName(), getParameter(fields[colIndex], cell.getContents()));
				}
				// 把tmpObject添加到list中
				list.add(tmpObject);
			}
		} catch (Exception e) {
			LOGGER.error("error:"+e.getMessage(), e);
		} 
		book.close();
		return list;
	}

	/**
	 * 把字符串转换成相应的类型的对象
	 * 
	 * @param field
	 *            属性
	 * @param msg
	 *            值
	 * @return 字符串转换成与属性类型一致的对象
	 */
	@SuppressWarnings("deprecation")
	private Object getParameter(Field field, String msg) {
		Object result = null;
		String nMsg = msg;
		try {
			String typeName = field.getType().getSimpleName();
			if ("int".equals(typeName) || "Integer".equals(typeName)) {
				if (nMsg.indexOf(".") > 0) {
					nMsg = nMsg.substring(0, nMsg.indexOf("."));
				}
				result = Integer.parseInt(nMsg);
			} else if ("double".equals(typeName) || "Double".equals(typeName)) {
				result = Double.parseDouble(nMsg);
			} else if ("boolean".equals(typeName) || "Boolean".equals(typeName)) {
				result = Boolean.parseBoolean(nMsg);
			} else if ("Date".equals(typeName)) {
				DateFormat dateFormat = new SimpleDateFormat((String) format.get(ExcelUtils.EXCEL_DATE_FORMAT));
				result = dateFormat.parse(nMsg);
			} else {
				result = nMsg;
			}
		} catch (Exception e) {
			LOGGER.error("error:"+e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 得到数据表头格式
	 * 
	 * @return
	 */
	public WritableCellFormat getTitleCellFormat() {
		WritableCellFormat wcf = null;
		try {
			// 字体样式(字体,大小,是否粗体,是否斜体)
			WritableFont wf = new WritableFont(WritableFont.TIMES, 11, WritableFont.BOLD, false);
			wcf = new WritableCellFormat(wf);// 实例化文字格式化
			// 对齐方式
			wcf.setAlignment(Alignment.CENTRE); // 水平
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直
		} catch (WriteException e) {
			LOGGER.error("error:"+e.getMessage(), e);
		}
		return wcf;
	}

	/**
	 * 得到数据格式(默认左对齐)
	 * 
	 * @return
	 */
	public WritableCellFormat getDataCellFormat(CellType type) {
		WritableCellFormat wcf = null;
		try {
			// 字体样式
			if (type == CellType.NUMBER || type == CellType.NUMBER_FORMULA) {// 数字
				NumberFormat nf = new NumberFormat("#.00"); // 保留小数点后两位
				wcf = new WritableCellFormat(nf);
			} else if (type == CellType.DATE || type == CellType.DATE_FORMULA) {// 日期
				jxl.write.DateFormat df = new jxl.write.DateFormat((String) format.get(EXCEL_DATE_FORMAT)); // 时间显示格式
				wcf = new jxl.write.WritableCellFormat(df);
			} else {
				WritableFont wf = new WritableFont(WritableFont.TIMES, 11, WritableFont.NO_BOLD, false);// 字体样式(字体,大小,是否粗体,是否斜体)
				wcf = new WritableCellFormat(wf);
			}
			// 对齐方式
			wcf.setAlignment(Alignment.LEFT);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);

			wcf.setWrap(false);// 自动换行
		} catch (WriteException e) {
			LOGGER.error("error:"+e.getMessage(), e);
		}

		return wcf;
	}

	/**
	 * 得到数据格式(重载) 可以设置水平对齐模式
	 * 
	 * @param type
	 * @param align
	 *            设置水平对齐模式
	 * @return
	 */
	public WritableCellFormat getDataCellFormat(CellType type, Alignment align) {
		WritableCellFormat wcf = null;
		try {
			// 字体样式
			if (type == CellType.NUMBER || type == CellType.NUMBER_FORMULA) {// 数字
				NumberFormat nf = new NumberFormat("#.00"); // 保留小数点后两位
				wcf = new WritableCellFormat(nf);
			} else if (type == CellType.DATE || type == CellType.DATE_FORMULA) {// 日期
				jxl.write.DateFormat df = new jxl.write.DateFormat((String) format.get(CellType.DATE)); // 时间显示格式
				wcf = new jxl.write.WritableCellFormat(df);
			} else {
				WritableFont wf = new WritableFont(WritableFont.TIMES, 12, WritableFont.NO_BOLD, false);// 字体样式(字体,大小,是否粗体,是否斜体)
				wcf = new WritableCellFormat(wf);
			}
			// 对齐方式
			wcf.setAlignment(align);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);

			wcf.setWrap(true);// 自动换行
		} catch (WriteException e) {
			LOGGER.error("error:"+e.getMessage(), e);
		}

		return wcf;
	}

	/**
	 * 生成并关闭工作簿
	 */
	public void writeAndClose() {
		try {
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			LOGGER.error("error:"+e.getMessage(), e);
		} 
	}

	/**
	 * 生成Excel文件(适合一个标题,剩余全是数据)
	 * 
	 * @param os
	 *            创建excel的流
	 * @param sheetName
	 *            工作表名称
	 * @param dataTitles
	 *            数据标题
	 * @param datas
	 *            数据--list中放行的内容，用list
	 */
	public void createExcelFile(OutputStream os, String sheetName, List<String> dataTitles, List<Object> datas) {

		try {

			// OutputStream os = new FileOutputStream(path);// 输出流指定文件路径
			workbook = Workbook.createWorkbook(os);// 创建工作薄
			sheet = workbook.createSheet(sheetName, 0); // 添加第一个工作表
			initialSheetSetting(sheet);// 初始化表格属性(公共方法)

			// 添加数据标题
			insertRowData(sheet, 0, dataTitles, getTitleCellFormat());

			// 插入一行 (公共方法)
			for (int i = 0; i < datas.size(); i++) {
				Object data = datas.get(i);
				insertRowData(sheet, i + 1, data);
			}

			workbook.write();
			workbook.close();

		} catch (Exception e) {
			LOGGER.error("error:"+e.getMessage(), e);
		}
	}

	/**
	 * 设置表格显示风格
	 * 
	 * @param cellType
	 *            用来设置表头显示风格，需为：EXCEL_TITLE_FORMAT或日期显示格式
	 * @param cellFormat
	 *            自己定义WritableCellFormat显示风格
	 */
	@SuppressWarnings("unchecked")
	public void setCellFormat(String cellType, WritableCellFormat cellFormat) {
		if (this.format != null && cellFormat != null) {
			try {
				if (format.get(cellType) == null) {
					throw new UserInputException("表格类型为空");
				} else {
					format.remove(cellType);
					format.put(cellType, cellFormat);
				}
			} catch (Exception e) {
				LOGGER.error("风格设置不正确。key应为：CellType或ExcelUtil的静态属性",e);
			}
		}
	}

	/**
	 * 设置显示风格
	 * 
	 * @param cellType
	 *            用来设置不同数据类型的显示格式，为CellType
	 * @param cellFormat
	 *            自己定义WritableCellFormat显示风格
	 */
	@SuppressWarnings("unchecked")
	public void setCellFormat(CellType cellType, WritableCellFormat cellFormat) {
		if (this.format != null && cellFormat != null) {
			try {
				if (format.get(cellType) == null) {
					throw new UserInputException("表格类型为空");
				} else {
					format.remove(cellType);
					format.put(cellType, cellFormat);
				}
			} catch (Exception e) {
				LOGGER.error("风格设置不正确。key应为：CellType或ExcelUtil的静态属性",e);
			}
		}
	}
	
	/**
	 * 读取excel内容返回 List(一列数字内容)
	 * @author achuang
	 * @create_date 2015年8月17日下午6:27:27
	 * @param 
	 * @return List<Object>
	 */
	@SuppressWarnings("unchecked")
	public List<String> readDataFromExcel(MultipartFile xlsFile, int inputFileSheetIndex, int rowNum) {
		String filename=xlsFile.getOriginalFilename();		
		List<String> list = new ArrayList<String>();
		org.apache.poi.ss.usermodel.Workbook book = null;
		org.apache.poi.ss.usermodel.Cell cell = null;
		try {
			if(filename.endsWith(".xlsx")){
				book=new XSSFWorkbook(xlsFile.getInputStream());
			} else {
				book = new HSSFWorkbook(xlsFile.getInputStream());
			}
	
			org.apache.poi.ss.usermodel.Sheet nSheet = book.getSheetAt(inputFileSheetIndex);
			for (int rowIndex = rowNum; rowIndex <= nSheet.getLastRowNum(); rowIndex++) {// 行
				for (int colIndex = 0; colIndex < nSheet.getRow(rowIndex).getLastCellNum(); colIndex++) {// 列					
					cell = nSheet.getRow(rowIndex).getCell(colIndex);
					String cellContent="";
					if(ObjectUtils.isNotEmpty(cell)&&cell.getCellType()==org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC){
						DecimalFormat df=new DecimalFormat("0");
						cellContent=df.format(cell.getNumericCellValue());
					} else {
						cellContent=cell.getStringCellValue();
					}
					 list.add(cellContent);
				}
			}
		} catch(Exception e){
			LOGGER.error("error:"+e.getMessage(), e);
			
		}
		return list;
	}
	
	
	
	/**
	 * admin
	 * @Title: readDataFromExcel
	 * @author administrator
	 * @Date: 2016年5月27日 上午10:36:51
	 * @param @param filename 文件名
	 * @param @param inputFile 输入流
	 * @param @param inputFileSheetIndex xls第几sheet
	 * @param @param rowNum 第一行开始读取
	 * @param @param bean
	 * @param @return
	 * @param @throws IOException
	 * @return List<Object>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Object> readDataFromExcel(String filename,InputStream inputFile, int inputFileSheetIndex, int rowNum, Class bean) throws Exception {
        
		List<Object> list = new ArrayList<Object>();
		org.apache.poi.ss.usermodel.Workbook book = null;
		org.apache.poi.ss.usermodel.Cell cell = null;
		if(filename.endsWith(".xlsx")){
			book=new XSSFWorkbook(inputFile);
		} else {
			book = new HSSFWorkbook(inputFile);
		}
		Object tmpObject = null;
		org.apache.poi.ss.usermodel.Sheet nSheet = book.getSheetAt(inputFileSheetIndex);
		for (int rowIndex = rowNum; rowIndex <= nSheet.getLastRowNum(); rowIndex++) {// 行
			// 实例一个bean
			tmpObject = bean.newInstance();
			// bean的属性，注意：bean中的属性顺序应该与表格中的属性一致。
			Field[] fields = bean.getDeclaredFields();
			for (int colIndex = 0; colIndex < nSheet.getRow(rowIndex).getLastCellNum(); colIndex++) {// 列
				cell = nSheet.getRow(rowIndex).getCell(colIndex);
				String cellContent="";
				if(ObjectUtils.isNotEmpty(cell)&&cell.getCellType()==org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC){
					DecimalFormat df=new DecimalFormat("0");
					cellContent=df.format(cell.getNumericCellValue());
				} else {
					cellContent=cell.getStringCellValue();
				}
				// 给tmpObject设置属性值
				CommonUtil.setProperty(tmpObject, fields[colIndex].getName(), getParameter(fields[colIndex],cellContent));
			}
			// 把tmpObject添加到list中
			list.add(tmpObject);
		}
		return list;
	}
	
}
