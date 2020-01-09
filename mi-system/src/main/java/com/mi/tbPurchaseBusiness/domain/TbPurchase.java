package com.mi.tbPurchaseBusiness.domain;

import com.mi.common.annotation.Excel;
import com.mi.common.core.domain.BaseEntity;
import com.mi.requirementsPlan.domain.TbRequirementsPlan;
import com.mi.system.domain.SysUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 采购计划对象 tb_purchase
 * 
 * @author lhp
 * @date 2019-11-13
 */
public class TbPurchase extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**
     * 需求计划编号
     */
    private Integer planId;

    /**
     * 需求计划信息
     */
    private  TbRequirementsPlan tbRequirementsPlan;

    /**
     * 采购员信息
     */
    private SysUser buyer;



    /** 采购编号 */
    @Excel(name = "采购编号")
    private Integer id;

    /** 采购员编号 */
    @Excel(name = "采购员编号")
    private Integer userId;

    /** 紧急 */
    @Excel(name = "紧急",readConverterExp = "0=轻,1=中,2=重,3=紧急")
    private Integer exigency;

    /** 状态 */
    @Excel(name = "状态",readConverterExp = "0=待采购,1=完成,2=撤销")
    private Integer status;

    /** 支付状态 */
    private Integer paymentState;

    public SysUser getBuyer() {
        return buyer;
    }

    public void setBuyer(SysUser buyer) {
        this.buyer = buyer;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public TbRequirementsPlan getTbRequirementsPlan() {
        return tbRequirementsPlan;
    }

    public void setTbRequirementsPlan(TbRequirementsPlan tbRequirementsPlan) {
        this.tbRequirementsPlan = tbRequirementsPlan;
    }
    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setUserId(Integer userId) 
    {
        this.userId = userId;
    }

    public Integer getUserId() 
    {
        return userId;
    }
    public void setExigency(Integer exigency) 
    {
        this.exigency = exigency;
    }

    public Integer getExigency() 
    {
        return exigency;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setPaymentState(Integer paymentState) 
    {
        this.paymentState = paymentState;
    }

    public Integer getPaymentState() 
    {
        return paymentState;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("exigency", getExigency())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("paymentState", getPaymentState())
            .append("updateTime", getUpdateTime())
            .append("createTime", getCreateTime())
            .toString();
    }
}
