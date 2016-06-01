package com.tyxj.bean.enums;

/**
 * @ClassName: EnumIllegalStatus
 * @Description: 设备是否使用非法滤芯货滤网
 * @author WangX
 * @Date: 2016年5月18日 下午5:34:06
 */
public enum EnumIllegalStatus {

	NORMAL("未使用",(byte)0), 
	ILLEGAL("使用",(byte)1);
	
	String name;
	byte status;
	EnumIllegalStatus(String name,byte status){
		this.name = name;
		this.status = status;
	}
	
	public static String getName(byte optype) {
		for (EnumIllegalStatus op : values()) {
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
