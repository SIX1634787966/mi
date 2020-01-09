package com.mi.supplierBusiness.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.mi.common.annotation.Excel;
import com.mi.common.core.domain.BaseEntity;

/**
 * 供应商对象 tb_supplier
 * 
 * @author lhp
 * @date 2019-11-12
 */
public class TbSupplier extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 供应商编号 */
    @Excel(name = "供应商编号")
    private Integer id;

    /** 厂商品牌 */
    @Excel(name = "厂商品牌")
    private String brand;

    /** 负责人 */
    @Excel(name = "负责人")
    private String president;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 联系地址 */
    @Excel(name = "联系地址")
    private String address;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setBrand(String brand) 
    {
        this.brand = brand;
    }

    public String getBrand() 
    {
        return brand;
    }
    public void setPresident(String president) 
    {
        this.president = president;
    }

    public String getPresident() 
    {
        return president;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("brand", getBrand())
            .append("president", getPresident())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("address", getAddress())
            .append("updateTime", getUpdateTime())
            .append("createTime", getCreateTime())
            .toString();
    }
}
