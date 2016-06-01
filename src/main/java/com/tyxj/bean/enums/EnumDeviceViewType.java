package com.tyxj.bean.enums;

/**
 * @ClassName: EnumDeviceType
 * @Description: 设备详细显示类型
 * @author WangX
 * @Date: 2016年5月18日 下午5:34:06
 */
public enum EnumDeviceViewType {
	
	SBXLH("设备序列号", 1),
	LX1("水滤芯1", 2),
	LX2("水滤芯2", 3), 
	LX3("水滤芯3", 4), 
	LX4("水滤芯4", 5), 
	LW("空气滤网", 6), 
	LXSM("滤芯剩余寿命", 7), 
	LWSM("滤网剩余寿命", 8), 
	YLZLBS("饮料种类杯数", 9), 
	ZBS("纸杯数", 10), 
	SJZT("水机状态", 11), 
	KFZT("咖啡机状态", 12), 
	KFJZTM("咖啡机故障代码", 13), 
	PM("pm值", 14), 
	YS("原水tds", 15), 
	JS("净水tds", 16),
	HJWD("环境温度", 17), 
	HJSD("环境湿度", 18), 
	VOC("voc", 19), 
	T2WD("T2温度", 20), 
	T2BWD("T2B温度", 21), 
	T3WD("T3温度", 22), 
	PQWD("排气温度", 23), 
	RSWD("热水温度", 24), 
	DSWD("冻水温度", 25), 
	SW1("水位1", 26), 
	SW2("水位2", 27), 
	SW3("水位3", 28), 
	LL1("流量1", 29), 
	LL2("流量2", 30),
	FL("风量", 31);
	
	String name;
	int status;
	EnumDeviceViewType(String name,int status){
		this.name = name;
		this.status = status;
	}
	
	public static String getName(int optype) {
		for (EnumDeviceViewType op : values()) {
			if (op.getStatus() == optype) {
				return op.name;
			}
		}
		return "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
