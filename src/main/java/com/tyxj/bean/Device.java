package com.tyxj.bean;

import java.io.Serializable;
import java.util.Date;

public class Device implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 设备序列号
     */
    private String deviceSerial;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 是否使用非法滤芯 0未使用 1使用
     */
    private Byte illegalStatus;

    /**
     * 匹配的滤芯滤网序列号
     */
    private String matchSerial;

    /**
     * 创建时间
     */
    private Date createTime;

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

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Byte getIllegalStatus() {
        return illegalStatus;
    }

    public void setIllegalStatus(Byte illegalStatus) {
        this.illegalStatus = illegalStatus;
    }

    public String getMatchSerial() {
        return matchSerial;
    }

    public void setMatchSerial(String matchSerial) {
        this.matchSerial = matchSerial;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}