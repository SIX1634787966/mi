package com.mi.supplierBusiness.service;

import com.mi.common.core.domain.Ztree;
import com.mi.supplierBusiness.domain.TbSupplier;
import java.util.List;

/**
 * 供应商Service接口
 * 
 * @author lhp
 * @date 2019-11-12
 */
public interface ITbSupplierService 
{
    /**
     * 查询供应商
     * 
     * @param id 供应商ID
     * @return 供应商
     */
    public TbSupplier selectTbSupplierById(Integer id);

    /**
     * 查询供应商列表
     * 
     * @param tbSupplier 供应商
     * @return 供应商集合
     */
    public List<TbSupplier> selectTbSupplierList(TbSupplier tbSupplier);

    /**
     * 新增供应商
     * 
     * @param tbSupplier 供应商
     * @return 结果
     */
    public int insertTbSupplier(TbSupplier tbSupplier);

    /**
     * 修改供应商
     * 
     * @param tbSupplier 供应商
     * @return 结果
     */
    public int updateTbSupplier(TbSupplier tbSupplier);

    /**
     * 批量删除供应商
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbSupplierByIds(String ids);

    /**
     * 删除供应商信息
     * 
     * @param id 供应商ID
     * @return 结果
     */
    public int deleteTbSupplierById(Integer id);

    List<Ztree> selectTbSupplierTree();

    String importTbSupplier(List<TbSupplier> tbSupplierList, boolean updateSupport, String operName);
}
