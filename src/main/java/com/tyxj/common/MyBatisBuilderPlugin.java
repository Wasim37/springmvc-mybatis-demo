package com.tyxj.common;

import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.ShellRunner;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Context;

/**
 * <pre>
 * add pagination using mysql limit.
 * This class is only used in ibator code generator.
 * </pre>
 */
public class MyBatisBuilderPlugin extends PluginAdapter {
	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		topLevelClass.addImportedType("java.io.Serializable");
		topLevelClass.addSuperInterface(new FullyQualifiedJavaType("java.io.Serializable"));

		Field field = new Field();
		//private static final long serialVersionUID = 1L;
		field.setName("serialVersionUID");
		field.setType(new FullyQualifiedJavaType("long"));
		field.setStatic(true);
		field.setFinal(true);
		field.setInitializationString("1L");
		topLevelClass.addField(field);
		return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
	}

	/* (non-Javadoc)
	 * @see org.mybatis.generator.api.PluginAdapter#setContext(org.mybatis.generator.config.Context)
	 */
	@Override
	public void setContext(Context context) {
		CommentGeneratorConfiguration cgc = new CommentGeneratorConfiguration();
		cgc.addProperty("suppressAllComments", "true");
		context.setCommentGeneratorConfiguration(cgc);
		//GeneratedXmlFile
		super.setContext(context);
	}

	@Override
	public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		field.addJavaDocLine("/**");
		field.addJavaDocLine(" * " +introspectedColumn.getRemarks());
		field.addJavaDocLine(" */");
		return super.modelFieldGenerated(field, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
	}

	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		// add field, getter, setter for limit clause
		addLimitParam(topLevelClass, introspectedTable, "limitStart");
		addLimitParam(topLevelClass, introspectedTable, "limitEnd");
		addLimit(topLevelClass, introspectedTable, "limit");
		return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
	}

	@Override
	public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

		element.addAttribute(new Attribute("keyProperty", introspectedTable.getPrimaryKeyColumns().get(0).getJavaProperty()));///设置主键返回策略
		element.addAttribute(new Attribute("useGeneratedKeys", "true"));
		return super.sqlMapInsertElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {//设置主键返回策略
		element.addAttribute(new Attribute("keyProperty", introspectedTable.getPrimaryKeyColumns().get(0).getJavaProperty()));
		element.addAttribute(new Attribute("useGeneratedKeys", "true"));
		return super.sqlMapInsertSelectiveElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		addLimitXml(element);
		return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		addLimitXml(element);
		return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
	}

	private void addLimitXml(XmlElement element) {
		/*XmlElement isParameterPresenteElemen = (XmlElement) element
		.getElements().get(element.getElements().size() - 1);*/
		XmlElement if1Element = new XmlElement("if"); //$NON-NLS-1$
		if1Element.addAttribute(new Attribute("test", "limit == null")); //$NON-NLS-1$ //$NON-NLS-2$
		
		XmlElement sonEleIf = new XmlElement("if"); //$NON-NLS-1$
		sonEleIf.addAttribute(new Attribute("test", "limitStart gt 0 and limitEnd gt 0 ")); //$NON-NLS-1$ //$NON-NLS-2$
		//isNotNullElement.addAttribute(new Attribute("compareValue", "0")); //$NON-NLS-1$ //$NON-NLS-2$
		sonEleIf.addElement(new TextElement("limit ${limitStart} , ${limitEnd}"));
		if1Element.addElement(sonEleIf);
		
		XmlElement sonEleElse = new XmlElement("if"); //$NON-NLS-1$
		sonEleElse.addAttribute(new Attribute("test", "limitStart lt 1 and limitEnd lt 0 ")); //$NON-NLS-1$ //$NON-NLS-2$
		//isNotNullElement.addAttribute(new Attribute("compareValue", "0")); //$NON-NLS-1$ //$NON-NLS-2$
		sonEleElse.addElement(new TextElement("limit ${limitEnd}"));
		if1Element.addElement(sonEleElse);
		element.addElement(if1Element);
		
		XmlElement elseSon = new XmlElement("if");
		elseSon.addAttribute(new Attribute("test", " limit != null ")); //$NON-NLS-1$ //$NON-NLS-2$
		//isNotNullElement.addAttribute(new Attribute("compareValue", "0")); //$NON-NLS-1$ //$NON-NLS-2$
		elseSon.addElement(new TextElement("limit ${limit.start} , ${limit.size}"));
		element.addElement(elseSon);
	}

	private void addLimitParam(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String name) {
		CommentGenerator commentGenerator = context.getCommentGenerator();
		Field field = new Field();
		field.setVisibility(JavaVisibility.PROTECTED);
		field.setType(FullyQualifiedJavaType.getIntInstance());
		field.setName(name);
		field.setInitializationString("0");
		commentGenerator.addFieldComment(field, introspectedTable);
		topLevelClass.addField(field);
		char c = name.charAt(0);
		String camel = Character.toUpperCase(c) + name.substring(1);
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("set" + camel);
		method.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), name));
		method.addBodyLine("this." + name + "=" + name + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
		method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(FullyQualifiedJavaType.getIntInstance());
		method.setName("get" + camel);
		method.addBodyLine("return " + name + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
	}

	private void addLimit(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String name) {
		CommentGenerator commentGenerator = context.getCommentGenerator();
		FullyQualifiedJavaType limitType = new FullyQualifiedJavaType("com.tyxj.common.Limit");
		topLevelClass.addImportedType(limitType);
		Field field = new Field();
		field.setVisibility(JavaVisibility.PROTECTED);
		field.setType(limitType);
		field.setName(name);
		commentGenerator.addFieldComment(field, introspectedTable);
		topLevelClass.addField(field);
		char c = name.charAt(0);
		String camel = Character.toUpperCase(c) + name.substring(1);
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("set" + camel);
		method.addParameter(new Parameter(limitType, name));
		method.addBodyLine("this." + name + "=" + name + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
		method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(limitType);
		method.setName("get" + camel);
		method.addBodyLine("return " + name + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
	}

	public static void generate() {
		String config = MyBatisBuilderPlugin.class.getClassLoader().getResource("generatorConfig.xml").getFile();
		String[] arg = { "-configfile", config, "-overwrite" };
		ShellRunner.main(arg);
	}

	public static void main(String[] args) {
		generate();
	}

	/**
	  * 去掉V 前缀 
	  * @author Jon Chiang
	  * @create_date 2014-5-8 下午3:44:00
	  * @param name
	  * @return
	 */
	private String getJavaName(String cname, String name) {
		if (cname.matches("._.*")) {
			char firstChar = Character.toLowerCase(name.charAt(1));
			name = name.substring(2, name.length());
			name = firstChar + name;
		}
		return name;
	}

	/* (non-Javadoc)
	 * @see org.mybatis.generator.api.Plugin#validate(java.util.List)
	 */
	@Override
	public boolean validate(List<String> arg0) {
		return true;
	}

	@Override
	public void initialized(IntrospectedTable introspectedTable) {
		List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
		for (IntrospectedColumn introspectedColumn : columns) {
			String javaProperty = this.getJavaName(introspectedColumn.getActualColumnName(), introspectedColumn.getJavaProperty());
			introspectedColumn.setJavaProperty(javaProperty);
		}
		super.initialized(introspectedTable);
	}

}
