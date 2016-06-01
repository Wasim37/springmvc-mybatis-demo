package com.tyxj.bean;

import java.io.Serializable;
import java.util.Date;

public class DeviceStatus implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 设备序列号
     */
    private String deviceSerial;

    /**
     * 水滤芯1
     */
    private String waterFilterSerial1;

    /**
     * 水滤芯2
     */
    private String waterFilterSerial2;

    /**
     * 水滤芯3
     */
    private String waterFilterSerial3;

    /**
     * 水滤芯4
     */
    private String waterFilterSerial4;

    /**
     * 空气滤网
     */
    private String airFilterSerial1;

    /**
     * 滤芯剩余寿命,分号分隔
     */
    private String waterFilterRemainTime;

    /**
     * 滤网剩余寿命,分号分隔
     */
    private String airFilterRemainTime;

    /**
     * 饮料种类杯数,分号分隔
     */
    private String drinkTypeNum;

    /**
     * 纸杯数
     */
    private Integer cups;

    /**
     * 水机状态
     */
    private Integer waterDeviceStatus;

    /**
     * 咖啡机状态
     */
    private Integer coffeeDeviceStatus;

    /**
     * 咖啡机故障代码
     */
    private Integer coffeeDeviceErrorCode;

    /**
     * pm值
     */
    private Integer pm;

    /**
     * 原水tds
     */
    private Integer rawWaterTds;

    /**
     * 净水tds
     */
    private Integer treatedWaterTds;

    /**
     * 环境温度
     */
    private Integer envirTemp;

    /**
     * 环境湿度
     */
    private Integer envirHumidity;

    /**
     * voc
     */
    private Integer voc;

    /**
     * T2温度
     */
    private Integer tempT2;

    /**
     * T2B温度
     */
    private Integer tempT2b;

    /**
     * T3温度
     */
    private Integer tempT3;

    /**
     * 排气温度
     */
    private Integer tempPq;

    /**
     * 热水温度
     */
    private Integer tempHot;

    /**
     * 冻水温度
     */
    private Integer tempIce;

    /**
     * 水位1
     */
    private Integer waterLevel1;

    /**
     * 水位2
     */
    private Integer waterLevel2;

    /**
     * 水位3
     */
    private Integer waterLevel3;

    /**
     * 流量1
     */
    private Integer flow1;

    /**
     * 流量2
     */
    private Integer flow2;

    /**
     * 风量
     */
    private Integer windSize;

    /**
     * 设备开机时间
     */
    private Integer deviceRunTime;

    /**
     * nfc字段
     */
    private String nfc;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 接收时间
     */
    private Date receiveTime;

    static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceSerial() {
        return deviceSerial;
    }

    public void setDeviceSerial(String deviceSerial) {
        this.deviceSerial = deviceSerial;
    }

    public String getWaterFilterSerial1() {
        return waterFilterSerial1;
    }

    public void setWaterFilterSerial1(String waterFilterSerial1) {
        this.waterFilterSerial1 = waterFilterSerial1;
    }

    public String getWaterFilterSerial2() {
        return waterFilterSerial2;
    }

    public void setWaterFilterSerial2(String waterFilterSerial2) {
        this.waterFilterSerial2 = waterFilterSerial2;
    }

    public String getWaterFilterSerial3() {
        return waterFilterSerial3;
    }

    public void setWaterFilterSerial3(String waterFilterSerial3) {
        this.waterFilterSerial3 = waterFilterSerial3;
    }

    public String getWaterFilterSerial4() {
        return waterFilterSerial4;
    }

    public void setWaterFilterSerial4(String waterFilterSerial4) {
        this.waterFilterSerial4 = waterFilterSerial4;
    }

    public String getAirFilterSerial1() {
        return airFilterSerial1;
    }

    public void setAirFilterSerial1(String airFilterSerial1) {
        this.airFilterSerial1 = airFilterSerial1;
    }

    public String getWaterFilterRemainTime() {
        return waterFilterRemainTime;
    }

    public void setWaterFilterRemainTime(String waterFilterRemainTime) {
        this.waterFilterRemainTime = waterFilterRemainTime;
    }

    public String getAirFilterRemainTime() {
        return airFilterRemainTime;
    }

    public void setAirFilterRemainTime(String airFilterRemainTime) {
        this.airFilterRemainTime = airFilterRemainTime;
    }

    public String getDrinkTypeNum() {
        return drinkTypeNum;
    }

    public void setDrinkTypeNum(String drinkTypeNum) {
        this.drinkTypeNum = drinkTypeNum;
    }

    public Integer getCups() {
        return cups;
    }

    public void setCups(Integer cups) {
        this.cups = cups;
    }

    public Integer getWaterDeviceStatus() {
        return waterDeviceStatus;
    }

    public void setWaterDeviceStatus(Integer waterDeviceStatus) {
        this.waterDeviceStatus = waterDeviceStatus;
    }

    public Integer getCoffeeDeviceStatus() {
        return coffeeDeviceStatus;
    }

    public void setCoffeeDeviceStatus(Integer coffeeDeviceStatus) {
        this.coffeeDeviceStatus = coffeeDeviceStatus;
    }

    public Integer getCoffeeDeviceErrorCode() {
        return coffeeDeviceErrorCode;
    }

    public void setCoffeeDeviceErrorCode(Integer coffeeDeviceErrorCode) {
        this.coffeeDeviceErrorCode = coffeeDeviceErrorCode;
    }

    public Integer getPm() {
        return pm;
    }

    public void setPm(Integer pm) {
        this.pm = pm;
    }

    public Integer getRawWaterTds() {
        return rawWaterTds;
    }

    public void setRawWaterTds(Integer rawWaterTds) {
        this.rawWaterTds = rawWaterTds;
    }

    public Integer getTreatedWaterTds() {
        return treatedWaterTds;
    }

    public void setTreatedWaterTds(Integer treatedWaterTds) {
        this.treatedWaterTds = treatedWaterTds;
    }

    public Integer getEnvirTemp() {
        return envirTemp;
    }

    public void setEnvirTemp(Integer envirTemp) {
        this.envirTemp = envirTemp;
    }

    public Integer getEnvirHumidity() {
        return envirHumidity;
    }

    public void setEnvirHumidity(Integer envirHumidity) {
        this.envirHumidity = envirHumidity;
    }

    public Integer getVoc() {
        return voc;
    }

    public void setVoc(Integer voc) {
        this.voc = voc;
    }

    public Integer getTempT2() {
        return tempT2;
    }

    public void setTempT2(Integer tempT2) {
        this.tempT2 = tempT2;
    }

    public Integer getTempT2b() {
        return tempT2b;
    }

    public void setTempT2b(Integer tempT2b) {
        this.tempT2b = tempT2b;
    }

    public Integer getTempT3() {
        return tempT3;
    }

    public void setTempT3(Integer tempT3) {
        this.tempT3 = tempT3;
    }

    public Integer getTempPq() {
        return tempPq;
    }

    public void setTempPq(Integer tempPq) {
        this.tempPq = tempPq;
    }

    public Integer getTempHot() {
        return tempHot;
    }

    public void setTempHot(Integer tempHot) {
        this.tempHot = tempHot;
    }

    public Integer getTempIce() {
        return tempIce;
    }

    public void setTempIce(Integer tempIce) {
        this.tempIce = tempIce;
    }

    public Integer getWaterLevel1() {
        return waterLevel1;
    }

    public void setWaterLevel1(Integer waterLevel1) {
        this.waterLevel1 = waterLevel1;
    }

    public Integer getWaterLevel2() {
        return waterLevel2;
    }

    public void setWaterLevel2(Integer waterLevel2) {
        this.waterLevel2 = waterLevel2;
    }

    public Integer getWaterLevel3() {
        return waterLevel3;
    }

    public void setWaterLevel3(Integer waterLevel3) {
        this.waterLevel3 = waterLevel3;
    }

    public Integer getFlow1() {
        return flow1;
    }

    public void setFlow1(Integer flow1) {
        this.flow1 = flow1;
    }

    public Integer getFlow2() {
        return flow2;
    }

    public void setFlow2(Integer flow2) {
        this.flow2 = flow2;
    }

    public Integer getWindSize() {
        return windSize;
    }

    public void setWindSize(Integer windSize) {
        this.windSize = windSize;
    }

    public Integer getDeviceRunTime() {
        return deviceRunTime;
    }

    public void setDeviceRunTime(Integer deviceRunTime) {
        this.deviceRunTime = deviceRunTime;
    }

    public String getNfc() {
        return nfc;
    }

    public void setNfc(String nfc) {
        this.nfc = nfc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }
}