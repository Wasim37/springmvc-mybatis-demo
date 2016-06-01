package com.tyxj.bean;

import java.io.Serializable;
import java.util.Date;

public class Function implements Serializable {
    /**
     * 功能模块id
     */
    private Integer funcId;

    /**
     * 功能名称
     */
    private String funName;

    /**
     * 父子级联关系
     */
    private Integer funPid;

    /**
     * 访问url
     */
    private String funUrl;

    /**
     * 菜单排序
     */
    private Byte funSeqNo;

    /**
     * 菜单级别。-1：非菜单 0：菜单根节点 1：1级菜单 2：2级菜单，依次类推
     */
    private Byte funLevel;

    /**
     * 用于控制图片显示的样式
     */
    private String funIconClass;

    /**
     * 创建时间
     */
    private Date funCreateAt;

    /**
     * 权限code值
     */
    private String funCode;

    static final long serialVersionUID = 1L;

    public Integer getFuncId() {
        return funcId;
    }

    public void setFuncId(Integer funcId) {
        this.funcId = funcId;
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName;
    }

    public Integer getFunPid() {
        return funPid;
    }

    public void setFunPid(Integer funPid) {
        this.funPid = funPid;
    }

    public String getFunUrl() {
        return funUrl;
    }

    public void setFunUrl(String funUrl) {
        this.funUrl = funUrl;
    }

    public Byte getFunSeqNo() {
        return funSeqNo;
    }

    public void setFunSeqNo(Byte funSeqNo) {
        this.funSeqNo = funSeqNo;
    }

    public Byte getFunLevel() {
        return funLevel;
    }

    public void setFunLevel(Byte funLevel) {
        this.funLevel = funLevel;
    }

    public String getFunIconClass() {
        return funIconClass;
    }

    public void setFunIconClass(String funIconClass) {
        this.funIconClass = funIconClass;
    }

    public Date getFunCreateAt() {
        return funCreateAt;
    }

    public void setFunCreateAt(Date funCreateAt) {
        this.funCreateAt = funCreateAt;
    }

    public String getFunCode() {
        return funCode;
    }

    public void setFunCode(String funCode) {
        this.funCode = funCode;
    }
}