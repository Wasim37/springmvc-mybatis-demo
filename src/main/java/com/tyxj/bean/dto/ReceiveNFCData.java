package com.tyxj.bean.dto;


import java.util.Date;

/**
 * @ClassName: ReceiveNFCData
 * @Description: TODO ADD FUNCTION.
 * @author LiDu
 * @Date: 2016年5月28日 下午4:03:57
 * 
 */
public class ReceiveNFCData {
	private Long consumablesId;  //耗材ID
	private Integer FactoryCode; //工厂代码
	private Long consumablesSerial; //耗材流水码
	private Date productionDate;	//制造日期
	private Date activationDate;  //使用日期
	public Long getConsumablesId() {
		return consumablesId;
	}
	public void setConsumablesId(Long consumablesId) {
		this.consumablesId = consumablesId;
	}
	public Integer getFactoryCode() {
		return FactoryCode;
	}
	public void setFactoryCode(Integer factoryCode) {
		FactoryCode = factoryCode;
	}
	public Long getConsumablesSerial() {
		return consumablesSerial;
	}
	public void setConsumablesSerial(Long consumablesSerial) {
		this.consumablesSerial = consumablesSerial;
	}
	public Date getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}
	public Date getActivationDate() {
		return activationDate;
	}
	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}
}
