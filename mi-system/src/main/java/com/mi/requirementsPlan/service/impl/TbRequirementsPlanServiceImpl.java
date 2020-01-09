package com.mi.requirementsPlan.service.impl;

import com.mi.common.core.domain.Ztree;
import com.mi.common.core.text.Convert;
import com.mi.common.utils.DateUtils;
import com.mi.materialsBusiness.domain.Tbmaterials;
import com.mi.materialsBusiness.mapper.TbmaterialsMapper;
import com.mi.requirementsPlan.domain.TbRequirementsPlan;
import com.mi.requirementsPlan.mapper.TbRequirementsPlanMapper;
import com.mi.requirementsPlan.service.ITbRequirementsPlanService;
import com.mi.supplierBusiness.mapper.TbSupplierMapper;
import com.mi.tbPurchaseBusiness.mapper.TbPurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 需求计划Service业务层处理
 * 
 * @author lhp
 * @date 2019-11-27
 */
@Service
public class TbRequirementsPlanServiceImpl implements ITbRequirementsPlanService 
{
    @Autowired
    private TbRequirementsPlanMapper tbRequirementsPlanMapper;
    @Autowired
    private TbmaterialsMapper tbmaterialsMapper;
    @Autowired
    private TbSupplierMapper tbSupplierMapper;
    @Autowired
    private TbPurchaseMapper tbPurchaseMapper;

    /**
     * 查询需求计划
     * 
     * @param id 需求计划ID
     * @return 需求计划
     */
    @Override
    public TbRequirementsPlan selectTbRequirementsPlanById(Integer id)
    {
        TbRequirementsPlan plan = tbRequirementsPlanMapper.selectTbRequirementsPlanById(id);
        plan.setTbmaterials(tbmaterialsMapper.selectTbmaterialsById(plan.getMaterialsId()));
        Tbmaterials tbmaterials = plan.getTbmaterials();
        tbmaterials.setTbSupplier(tbSupplierMapper.selectTbSupplierById(tbmaterials.getSupplierId()));
        return plan;
    }

    /**
     * 查询需求计划列表
     * 
     * @param tbRequirementsPlan 需求计划
     * @return 需求计划
     */
    @Override
    public List<TbRequirementsPlan> selectTbRequirementsPlanList(TbRequirementsPlan tbRequirementsPlan)
    {
        List<TbRequirementsPlan> plans = tbRequirementsPlanMapper.selectTbRequirementsPlanList(tbRequirementsPlan);
        Iterator<TbRequirementsPlan> iterator = plans.iterator();
        while (iterator.hasNext()){
            TbRequirementsPlan next = iterator.next();
            next.setTbmaterials(tbmaterialsMapper.selectTbmaterialsById(next.getMaterialsId()));
            Tbmaterials tbmaterials = next.getTbmaterials();
            tbmaterials.setTbSupplier(tbSupplierMapper.selectTbSupplierById(tbmaterials.getSupplierId()));

        }
        return plans;
    }

    /**
     * 新增需求计划
     * 
     * @param tbRequirementsPlan 需求计划
     * @return 结果
     */
    @Override
    public int insertTbRequirementsPlan(TbRequirementsPlan tbRequirementsPlan)
    {
        tbRequirementsPlan.setCreateTime(DateUtils.getNowDate());
        return tbRequirementsPlanMapper.insertTbRequirementsPlan(tbRequirementsPlan);
    }

    /**
     * 修改需求计划
     * 
     * @param tbRequirementsPlan 需求计划
     * @return 结果
     */
    @Override
    public int updateTbRequirementsPlan(TbRequirementsPlan tbRequirementsPlan)
    {
        tbRequirementsPlan.setUpdateTime(DateUtils.getNowDate());
        return tbRequirementsPlanMapper.updateTbRequirementsPlan(tbRequirementsPlan);
    }

    /**
     * 删除需求计划对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteTbRequirementsPlanByIds(String ids)
    {
        String[] strings = Convert.toStrArray(ids);
       int res =tbPurchaseMapper.deleteTbPurchaseByPlanIds(strings);

        return tbRequirementsPlanMapper.deleteTbRequirementsPlanByIds(strings);
    }

    /**
     * 删除需求计划信息
     * 
     * @param id 需求计划ID
     * @return 结果
     */
    public int deleteTbRequirementsPlanById(Integer id)
    {
        return tbRequirementsPlanMapper.deleteTbRequirementsPlanById(id);
    }

    /**
     * 需求计划选择树
     * @return Ztree需求计划数据集合
     */
    @Override
    public List<Ztree> selectRequirementsPlanTree() {
        TbRequirementsPlan t=new TbRequirementsPlan();
        t.setStatus(0);
        List<TbRequirementsPlan> plans=  tbRequirementsPlanMapper.selectTbRequirementsPlanList(t);
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (TbRequirementsPlan tbRequirementsPlan : plans) {

            Ztree ztree=new Ztree();
            ztree.setId(tbRequirementsPlan.getId().longValue());
            ztree.setName(tbRequirementsPlan.getTitle());
            ztree.setTitle(tbRequirementsPlan.getTitle());
            ztrees.add(ztree);
        }
        return  ztrees;
    }
}
