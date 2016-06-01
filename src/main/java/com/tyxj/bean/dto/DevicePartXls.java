package com.tyxj.bean.dto;

import org.jeecgframework.poi.excel.annotation.Excel;

public class DevicePartXls {
	
	@Excel(name="配件序列号")
	private String devicePartSerial;
	
	@Excel(name="配件名称")
	private String devicePartName;
	
	@Excel(name="配件类型")
	private String devicePartType;
	public String getDevicePartSerial() {
		return devicePartSerial;
	}

	public void setDevicePartSerial(String devicePartSerial) {
		this.devicePartSerial = devicePartSerial;
	}

	public String getDevicePartType() {
		return devicePartType;
	}

	public void setDevicePartType(String devicePartType) {
		this.devicePartType = devicePartType;
	}

	public String getDevicePartName() {
		return devicePartName;
	}

	public void setDevicePartName(String devicePartName) {
		this.devicePartName = devicePartName;
	}

	
}
