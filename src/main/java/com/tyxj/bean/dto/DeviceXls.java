package com.tyxj.bean.dto;

import org.jeecgframework.poi.excel.annotation.Excel;

public class DeviceXls {
	
	@Excel(name="设备序列号")
	private String deviceSerial;
	
	@Excel(name="设备名称")
	private String deviceName;

	public String getDeviceSerial() {
		return deviceSerial;
	}

	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
}
