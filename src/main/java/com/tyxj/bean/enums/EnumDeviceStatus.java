package com.tyxj.bean.enums;

/**
 * @ClassName: EnumDeviceStatus
 * @Description: 设备是否配对
 * @author WangX
 * @Date: 2016年5月18日 下午5:34:06
 */
public enum EnumDeviceStatus {

	NORMAL("未配对",0), 
	PAIR("已配对",1);
	
	String name;
	int status;
	EnumDeviceStatus(String name,int status){
		this.name = name;
		this.status = status;
	}
	
	public static String getName(int optype) {
		for (EnumDeviceStatus op : values()) {
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
