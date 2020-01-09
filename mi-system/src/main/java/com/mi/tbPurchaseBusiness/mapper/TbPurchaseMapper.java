package com.mi.tbPurchaseBusiness.mapper;

import com.mi.tbPurchaseBusiness.domain.TbPurchase;
import java.util.List;

/**
 * 采购计划Mapper接口
 * 
 * @author lhp
 * @date 2019-11-13
 */
public interface TbPurchaseMapper 
{
    /**
     * 查询采购计划
     * 
     * @param id 采购计划ID
     * @return 采购计划
     */
    public TbPurchase selectTbPurchaseById(Integer id);

    /**
     * 查询采购计划列表
     * 
     * @param tbPurchase 采购计划
     * @return 采购计划集合
     */
    public List<TbPurchase> selectTbPurchaseList(TbPurchase tbPurchase);

    /**
     * 新增采购计划
     * 
     * @param tbPurchase 采购计划
     * @return 结果
     */
    public int insertTbPurchase(TbPurchase tbPurchase);

    /**
     * 修改采购计划
     * 
     * @param tbPurchase 采购计划
     * @return 结果
     */
    public int updateTbPurchase(TbPurchase tbPurchase);

    /**
     * 删除采购计划
     * 
     * @param id 采购计划ID
     * @return 结果
     */
    public int deleteTbPurchaseById(Integer id);

    /**
     * 批量删除采购计划
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbPurchaseByIds(String[] ids);
    /**
     * 批量删除采购计划
     *
     * @param ids 需要删除的采购计划ID
     * @return 结果
     */
    int deleteTbPurchaseByPlanIds(String[] ids);
}
