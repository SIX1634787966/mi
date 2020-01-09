package com.mi.warehouseBusiness.service;

import com.mi.common.core.domain.Ztree;
import com.mi.warehouseBusiness.domain.TbWarehouse;
import java.util.List;

/**
 * 仓库管理Service接口
 * 
 * @author lhp
 * @date 2019-11-12
 */
public interface ITbWarehouseService 
{
    /**
     * 查询仓库管理
     * 
     * @param id 仓库管理ID
     * @return 仓库管理
     */
    public TbWarehouse selectTbWarehouseById(Integer id);

    /**
     * 查询仓库管理列表
     * 
     * @param tbWarehouse 仓库管理
     * @return 仓库管理集合
     */
    public List<TbWarehouse> selectTbWarehouseList(TbWarehouse tbWarehouse);

    /**
     * 新增仓库管理
     * 
     * @param tbWarehouse 仓库管理
     * @return 结果
     */
    public int insertTbWarehouse(TbWarehouse tbWarehouse);

    /**
     * 修改仓库管理
     * 
     * @param tbWarehouse 仓库管理
     * @return 结果
     */
    public int updateTbWarehouse(TbWarehouse tbWarehouse);

    /**
     * 批量删除仓库管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbWarehouseByIds(String ids);

    /**
     * 删除仓库管理信息
     * 
     * @param id 仓库管理ID
     * @return 结果
     */
    public int deleteTbWarehouseById(Integer id);

    List<Ztree> selectWarehouseTree();

    String importWarehouse(List<TbWarehouse> tbWarehouseList, boolean updateSupport, String operName);
}
