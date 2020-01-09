package com.mi.materialsBusiness.service;

import com.mi.common.core.domain.Ztree;
import com.mi.materialsBusiness.domain.Tbmaterials;
import com.mi.system.domain.SysUser;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 物资管理Service接口
 * 
 * @author lhp
 * @date 2019-11-12
 */
public interface ITbmaterialsService 
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
     * 批量删除物资管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbmaterialsByIds(String ids);

    /**
     * 删除物资管理信息
     * 
     * @param id 物资管理ID
     * @return 结果
     */
    public int deleteTbmaterialsById(Integer id);
    /**
     * 查询物资
     * @return 物资列表
     */
    List<Ztree> selectMaterialsTree();
    /**
     * 导入物资
     * @param tbmaterialsList 物资列表
     * @return 物资列表
     */
    String importMaterials(List<Tbmaterials> tbmaterialsList, boolean updateSupport, String operName);

    Integer updateAmountById(Tbmaterials tbmaterials, SysUser user, Integer type, HttpSession session);
}
