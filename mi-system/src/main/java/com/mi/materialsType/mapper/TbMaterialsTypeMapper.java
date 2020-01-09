package com.mi.materialsType.mapper;

import com.mi.materialsBusiness.domain.Tbmaterials;
import com.mi.materialsType.domain.TbMaterialsType;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 物资类型Mapper接口
 * 
 * @author lhp
 * @date 2019-11-27
 */
public interface TbMaterialsTypeMapper 
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
     * 删除物资类型
     * 
     * @param id 物资类型ID
     * @return 结果
     */
    public int deleteTbMaterialsTypeById(Integer id);

    /**
     * 批量删除物资类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbMaterialsTypeByIds(String[] ids);
    @Update("update tb_materials set amount=amount-#{amount} where id=#{id}")
    Integer updateAmountById(Tbmaterials tbmaterials);
}
