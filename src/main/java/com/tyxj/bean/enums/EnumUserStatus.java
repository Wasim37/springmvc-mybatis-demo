package com.tyxj.bean.enums;

/**
 * @ClassName: EnumUserStatus
 * @Description: 用户状态枚举类
 * @author WangX
 * @Date: 2016年5月18日 下午5:34:06
 */
public enum EnumUserStatus {

	PAUSE("暂停",0), 
	NORMAL("正常",1);
	
	String name;
	int status;
	EnumUserStatus(String name,int status){
		this.name = name;
		this.status = status;
	}
	
	public static String getName(int optype) {
		for (EnumUserStatus op : values()) {
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
