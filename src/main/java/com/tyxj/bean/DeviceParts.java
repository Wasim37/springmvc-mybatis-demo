package com.tyxj.bean;

import java.io.Serializable;
import java.util.Date;

public class DeviceParts implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 配件序列号
     */
    private String partSerial;

    /**
     * 配件名称
     */
    private String partName;

    /**
     * 配件类型 1 滤芯 2滤网
     */
    private Byte partType;

    /**
     * 是否配对 0未配对 1配对
     */
    private Byte partStatus;

    /**
     * 设备id
     */
    private Integer deviceId;

    /**
     * 设备序列号
     */
    private String deviceSerial;

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

    public String getPartSerial() {
        return partSerial;
    }

    public void setPartSerial(String partSerial) {
        this.partSerial = partSerial;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Byte getPartType() {
        return partType;
    }

    public void setPartType(Byte partType) {
        this.partType = partType;
    }

    public Byte getPartStatus() {
        return partStatus;
    }

    public void setPartStatus(Byte partStatus) {
        this.partStatus = partStatus;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceSerial() {
        return deviceSerial;
    }

    public void setDeviceSerial(String deviceSerial) {
        this.deviceSerial = deviceSerial;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}