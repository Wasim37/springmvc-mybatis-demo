package com.tyxj.bean.enums;

/**
 * @ClassName: EnumDeviceType
 * @Description: 设备配件类型
 * @author WangX
 * @Date: 2016年5月18日 下午5:34:06
 */
public enum EnumDevicePartType {

	LVXIN("滤芯",(byte)1), 
	LVWANG("滤网",(byte)2);
	
	String name;
	byte status;
	EnumDevicePartType(String name,byte status){
		this.name = name;
		this.status = status;
	}
	
	public static String getName(byte optype) {
		for (EnumDevicePartType op : values()) {
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

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}
}
