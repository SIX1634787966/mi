package com.mi.bill.domain;

import com.mi.common.annotation.Excel;
import com.mi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 凭条对象 tb_bill
 * 
 * @author lhp
 * @date 2019-12-19
 */
public class TbBill extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 商品详情ID */
    @Excel(name = "商品详情ID")
    private Long detailId;

    /** 类型 */
    @Excel(name = "类型")
    private Integer type;

    /** 物资名 */
    @Excel(name = "物资名")
    private String materialsName;

    /** 数量 */
    @Excel(name = "数量")
    private Integer acoumt;

    /** 操作员 */
    @Excel(name = "操作员")
    private String handler;

    /** 金额 */
    @Excel(name = "金额")
    private Double money;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDetailId(Long detailId)
    {
        this.detailId = detailId;
    }

    public Long getDetailId()
    {
        return detailId;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setMaterialsName(String materialsName) 
    {
        this.materialsName = materialsName;
    }

    public String getMaterialsName() 
    {
        return materialsName;
    }
    public void setAcoumt(Integer acoumt)
    {
        this.acoumt = acoumt;
    }

    public Integer getAcoumt()
    {
        return acoumt;
    }
    public void setHandler(String handler) 
    {
        this.handler = handler;
    }

    public String getHandler() 
    {
        return handler;
    }
    public void setMoney(Double money)
    {
        this.money = money;
    }

    public Double getMoney()
    {
        return money;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("detailId", getDetailId())
            .append("type", getType())
            .append("materialsName", getMaterialsName())
            .append("acoumt", getAcoumt())
            .append("handler", getHandler())
            .append("money", getMoney())
            .append("updateTime", getUpdateTime())
            .append("createTime", getCreateTime())
            .toString();
    }
}
