package com.mi.requirementsPlan.domain;

import com.mi.common.annotation.Excel;
import com.mi.common.core.domain.BaseEntity;
import com.mi.materialsBusiness.domain.Tbmaterials;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 需求计划对象 tb_requirements_plan
 * 
 * @author lhp
 * @date 2019-11-27
 */

public class TbRequirementsPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    /** 编号 */
    @Excel(name = "编号")
    private Integer id;

    /** 物资编号 */
    @Excel(name = "物资编号")
    private Integer materialsId;

    /** 物资信息 */
    private Tbmaterials tbmaterials;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 需求数量 */
    @Excel(name = "需求数量")
    private Integer amount;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    public Tbmaterials getTbmaterials() {
        return tbmaterials;
    }

    public void setTbmaterials(Tbmaterials tbmaterials) {
        this.tbmaterials = tbmaterials;
    }
    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setMaterialsId(Integer materialsId) 
    {
        this.materialsId = materialsId;
    }

    public Integer getMaterialsId() 
    {
        return materialsId;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setAmount(Integer amount) 
    {
        this.amount = amount;
    }

    public Integer getAmount() 
    {
        return amount;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("materialsId", getMaterialsId())
            .append("title", getTitle())
            .append("amount", getAmount())
            .append("status", getStatus())
            .append("updateTime", getUpdateTime())
            .append("createTime", getCreateTime())
            .toString();
    }
}
