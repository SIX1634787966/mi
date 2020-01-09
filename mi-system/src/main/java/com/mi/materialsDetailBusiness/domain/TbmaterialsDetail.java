package com.mi.materialsDetailBusiness.domain;

import com.mi.common.annotation.Excel;
import com.mi.common.core.domain.BaseEntity;
import com.mi.materialsBusiness.domain.Tbmaterials;

/**
 * 物资出入库详情对象 tb_ materials_detail
 * 
 * @author mi
 * @date 2019-11-19
 */
public class TbmaterialsDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "TbmaterialsDetail{" +
                "tbmaterials=" + tbmaterials +
                ", id=" + id +
                ", userId=" + userId +
                ", materialsId=" + materialsId +
                ", purchaseId=" + purchaseId +
                ", amount=" + amount +
                ", type=" + type +
                '}';
    }

    /**
     * 物资信息
     */
    private Tbmaterials tbmaterials;
    /** 编号 */
    @Excel(name = "编号")
    private Long id;

    /** 操作员编号 */
    @Excel(name = "操作员编号")
    private Long userId;

    /** 物资编号 */
    @Excel(name = "物资编号")
    private Integer materialsId;

    /** 采购编号 */
    private Integer purchaseId;

    /** 数量 */
    @Excel(name = "数量")
    private Integer amount;

    /** 类型 */
    @Excel(name = "类型")
    private Integer type;



    public Tbmaterials getTbmaterials() {
        return tbmaterials;
    }

    public void setTbmaterials(Tbmaterials tbmaterials) {
        this.tbmaterials = tbmaterials;
    }
    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setMaterialsId(Integer materialsId) 
    {
        this.materialsId = materialsId;
    }

    public Integer getMaterialsId() 
    {
        return materialsId;
    }
    public void setPurchaseId(Integer purchaseId) 
    {
        this.purchaseId = purchaseId;
    }

    public Integer getPurchaseId() 
    {
        return purchaseId;
    }
    public void setAmount(Integer amount) 
    {
        this.amount = amount;
    }

    public Integer getAmount() 
    {
        return amount;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }

}
