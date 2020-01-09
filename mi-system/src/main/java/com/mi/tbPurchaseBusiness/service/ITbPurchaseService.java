package com.mi.tbPurchaseBusiness.service;

import com.mi.system.domain.SysUser;
import com.mi.tbPurchaseBusiness.domain.TbPurchase;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 采购计划Service接口
 * 
 * @author lhp
 * @date 2019-11-13
 */
public interface ITbPurchaseService 
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
    public int updateTbPurchase(TbPurchase tbPurchase,Long userId);

    /**
     * 批量删除采购计划
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbPurchaseByIds(String ids);

    /**
     * 删除采购计划信息
     * 
     * @param id 采购计划ID
     * @return 结果
     */
    public int deleteTbPurchaseById(Integer id);

    /**
     * 验收物资
     * @param id
     * @param status
     * @param materialsId
     * @param amount
     * @return
     */
    int accept(Integer id, Integer status, Integer materialsId, Integer amount, Integer planId, SysUser user, HttpSession session);
}
