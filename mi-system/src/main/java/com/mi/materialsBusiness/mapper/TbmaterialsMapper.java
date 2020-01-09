package com.mi.materialsBusiness.mapper;

import com.mi.materialsBusiness.domain.Tbmaterials;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 物资管理Mapper接口
 * 
 * @author lhp
 * @date 2019-11-12
 */
public interface TbmaterialsMapper 
{
    /**
     * 查询物资管理
     * 
     * @param id 物资管理ID
     * @return 物资管理
     */
    public Tbmaterials selectTbmaterialsById(Integer id);

    /**
     * 查询物资管理列表
     * 
     * @param tbmaterials 物资管理
     * @return 物资管理集合
     */
    public List<Tbmaterials> selectTbmaterialsList(Tbmaterials tbmaterials);

    /**
     * 新增物资管理
     * 
     * @param tbmaterials 物资管理
     * @return 结果
     */
    public int insertTbmaterials(Tbmaterials tbmaterials);

    /**
     * 修改物资管理
     * 
     * @param tbmaterials 物资管理
     * @return 结果
     */
    public int updateTbmaterials(Tbmaterials tbmaterials);

    /**
     * 删除物资管理
     * 
     * @param id 物资管理ID
     * @return 结果
     */
    public int deleteTbmaterialsById(Integer id);

    /**
     * 批量删除物资管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbmaterialsByIds(String[] ids);
    /**
     * 查询物资
     * @return 物资列表
     */
    List<Tbmaterials> selectMaterialsTree();
    @Select("select amount from tb_materials where id=#{materialsId}")
    Integer  checkAmount(Integer materialsId);
    @Update("update tb_materials set amount=amount+#{amount} where id=#{id}")
    Integer updateAmount(@Param("id") Integer id,@Param("amount") Integer amount);
    @Update("update tb_materials set amount=amount-#{amount} where id=#{id}")
    Integer updateAmountById(Tbmaterials tbmaterials);
}
