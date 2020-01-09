package com.mi.materialsType.service;

import com.mi.common.core.domain.Ztree;
import com.mi.materialsType.domain.TbMaterialsType;
import java.util.List;

/**
 * 物资类型Service接口
 * 
 * @author lhp
 * @date 2019-11-27
 */
public interface ITbMaterialsTypeService 
{
    /**
     * 查询物资类型
     * 
     * @param id 物资类型ID
     * @return 物资类型
     */
    public TbMaterialsType selectTbMaterialsTypeById(Integer id);

    /**
     * 查询物资类型列表
     * 
     * @param tbMaterialsType 物资类型
     * @return 物资类型集合
     */
    public List<TbMaterialsType> selectTbMaterialsTypeList(TbMaterialsType tbMaterialsType);

    /**
     * 新增物资类型
     * 
     * @param tbMaterialsType 物资类型
     * @return 结果
     */
    public int insertTbMaterialsType(TbMaterialsType tbMaterialsType);

    /**
     * 修改物资类型
     * 
     * @param tbMaterialsType 物资类型
     * @return 结果
     */
    public int updateTbMaterialsType(TbMaterialsType tbMaterialsType);

    /**
     * 批量删除物资类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbMaterialsTypeByIds(String ids);

    /**
     * 删除物资类型信息
     * 
     * @param id 物资类型ID
     * @return 结果
     */
    public int deleteTbMaterialsTypeById(Integer id);

    List<Ztree> selectMaterialsTypeTree();
}
