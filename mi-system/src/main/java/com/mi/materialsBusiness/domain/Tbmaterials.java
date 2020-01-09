package com.mi.materialsBusiness.domain;

import com.mi.common.annotation.Excel;
import com.mi.common.core.domain.BaseEntity;
import com.mi.materialsType.domain.TbMaterialsType;
import com.mi.supplierBusiness.domain.TbSupplier;
import com.mi.warehouseBusiness.domain.TbWarehouse;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 物资管理对象 tb_materials
 * 
 * @author lhp
 * @date 2019-11-12
 */
public class Tbmaterials extends BaseEntity
{
    private static final long serialVersionUID = 1L;



    /**
     * 仓库信息
     */
    private TbWarehouse tbWarehouse;
    /**
     * 供应商信息
     */
    private TbSupplier tbSupplier;
    /** 物资编号 */
    @Excel(name = "物资编号")
    private Integer id;
    /** 类型编号 */
    @Excel(name = "类型编号")
    private Integer typeId;

    @Excel(name = "类型名", targetAttr = "name")
    /** 类型信息 */
    private TbMaterialsType tbMaterialsType;
    /** 供应商编号 */
    @Excel(name = "供应商编号")
    private Integer supplierId;


    /** 仓库编号 */
    @Excel(name = "仓库编号")
    private Integer warehouseId;

    /** 款号 */
    @Excel(name = "款号")
    private String styleNumber;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 产地 */
    @Excel(name = "产地")
    private String originSource;

    /** 规格 */
    @Excel(name = "规格")
    private String specification;

    /** 数量 */
    @Excel(name = "数量")
    private Integer amount;

    /** 质量 */
    @Excel(name = "质量")
    private Double quality;

    /** 质量计量 */
    @Excel(name = "质量计量",readConverterExp = "0=克,1=千克,2=吨")
    private Long unit;

    /** 入库价 */
    @Excel(name = "入库价")
    private Long putPrice;

    /** 出库价 */
    @Excel(name = "出库价")
    private Long deliveryPrice;

    /** 单位计量 */
    @Excel(name = "单位计量",readConverterExp = "0=台,1=辆")
    private Integer units;
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public TbMaterialsType getTbMaterialsType() {
        return tbMaterialsType;
    }

    public void setTbMaterialsType(TbMaterialsType tbMaterialsType) {
        this.tbMaterialsType = tbMaterialsType;
    }
    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /** 备注 */
    @Excel(name = "备注")
    private String remark;
    public Integer getUnits() {return units; }
    public void setUnits(Integer units) {this.units = units;}

    public TbSupplier getTbSupplier() {
        return tbSupplier;
    }
    public void setTbSupplier(TbSupplier tbSupplier) {
        this.tbSupplier = tbSupplier;
    }
    public TbWarehouse getTbWarehouse() {
        return tbWarehouse;
    }
    public void setTbWarehouse(TbWarehouse tbWarehouse) {
        this.tbWarehouse = tbWarehouse;
    }
    public void setId(Integer id) 
    {
        this.id = id;
    }
    public Integer getId() 
    {
        return id;
    }
    public void setSupplierId(Integer supplierId) 
    {
        this.supplierId = supplierId;
    }
    public Integer getSupplierId() 
    {
        return supplierId;
    }
    public void setWarehouseId(Integer warehouseId) 
    {
        this.warehouseId = warehouseId;
    }
    public Integer getWarehouseId() 
    {
        return warehouseId;
    }
    public void setStyleNumber(String styleNumber) 
    {
        this.styleNumber = styleNumber;
    }
    public String getStyleNumber() 
    {
        return styleNumber;
    }
    public void setName(String name) 
    {
        this.name = name;
    }
    public String getName() 
    {
        return name;
    }
    public void setOriginSource(String originSource) 
    {
        this.originSource = originSource;
    }
    public String getOriginSource() 
    {
        return originSource;
    }
    public void setSpecification(String specification) 
    {
        this.specification = specification;
    }
    public String getSpecification() 
    {
        return specification;
    }
    public void setAmount(Integer amount) 
    {
        this.amount = amount;
    }
    public Integer getAmount() 
    {
        return amount;
    }
    public void setQuality(Double quality) 
    {
        this.quality = quality;
    }
    public Double getQuality() 
    {
        return quality;
    }
    public void setUnit(Long unit) 
    {
        this.unit = unit;
    }
    public Long getUnit() 
    {
        return unit;
    }
    public void setPutPrice(Long putPrice) 
    {
        this.putPrice = putPrice;
    }
    public Long getPutPrice() 
    {
        return putPrice;
    }
    public void setDeliveryPrice(Long deliveryPrice) 
    {
        this.deliveryPrice = deliveryPrice;
    }
    public Long getDeliveryPrice() 
    {
        return deliveryPrice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("supplierId", getSupplierId())
            .append("warehouseId", getWarehouseId())
            .append("styleNumber", getStyleNumber())
            .append("name", getName())
            .append("originSource", getOriginSource())
            .append("specification", getSpecification())
            .append("amount", getAmount())
            .append("quality", getQuality())
            .append("unit", getUnit())
            .append("putPrice", getPutPrice())
            .append("deliveryPrice", getDeliveryPrice())
            .append("remark", getRemark())
            .append("updateTime", getUpdateTime())
            .append("createTime", getCreateTime())
             .append("Units", getUnits())
            .toString();
    }
}
