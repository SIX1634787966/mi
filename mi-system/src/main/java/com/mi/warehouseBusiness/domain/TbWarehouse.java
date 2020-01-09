package com.mi.warehouseBusiness.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.mi.common.annotation.Excel;
import com.mi.common.core.domain.BaseEntity;

/**
 * 仓库管理对象 tb_warehouse
 * 
 * @author lhp
 * @date 2019-11-12
 */
public class TbWarehouse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 仓库编号 */
    @Excel(name = "仓库编号")
    private Integer id;

    /** 仓库名称 */
    @Excel(name = "仓库名称")
    private String name;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 仓库地址 */
    @Excel(name = "仓库地址")
    private String address;

    /** 负责人 */
    @Excel(name = "负责人")
    private String president;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 状态 */
    @Excel(name = "状态",readConverterExp = "0=正常,1=停用")
    private Integer status;

    /** 面积m² */
    @Excel(name = "面积m²")
    private Double area;

    /** 可用 */
    @Excel(name = "可用",readConverterExp = "0=可用,1=满载")
    private Integer isfull;

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Excel(name = "备注²")
    /** 备注 */
    private String remark;
    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setPresident(String president) 
    {
        this.president = president;
    }

    public String getPresident() 
    {
        return president;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setArea(Double area) 
    {
        this.area = area;
    }

    public Double getArea() 
    {
        return area;
    }
    public void setIsfull(Integer isfull) 
    {
        this.isfull = isfull;
    }

    public Integer getIsfull() 
    {
        return isfull;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("phone", getPhone())
            .append("address", getAddress())
            .append("president", getPresident())
            .append("email", getEmail())
            .append("status", getStatus())
            .append("area", getArea())
            .append("isfull", getIsfull())
            .append("remark", getRemark())
            .append("updateTime", getUpdateTime())
            .append("createTime", getCreateTime())
            .toString();
    }
}
