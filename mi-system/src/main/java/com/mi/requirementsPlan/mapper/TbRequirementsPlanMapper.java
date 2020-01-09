package com.mi.requirementsPlan.mapper;

import com.mi.requirementsPlan.domain.TbRequirementsPlan;
import java.util.List;

/**
 * 需求计划Mapper接口
 * 
 * @author lhp
 * @date 2019-11-27
 */
public interface TbRequirementsPlanMapper 
{
    /**
     * 查询需求计划
     * 
     * @param id 需求计划ID
     * @return 需求计划
     */
    public TbRequirementsPlan selectTbRequirementsPlanById(Integer id);

    /**
     * 查询需求计划列表
     * 
     * @param tbRequirementsPlan 需求计划
     * @return 需求计划集合
     */
    public List<TbRequirementsPlan> selectTbRequirementsPlanList(TbRequirementsPlan tbRequirementsPlan);

    /**
     * 新增需求计划
     * 
     * @param tbRequirementsPlan 需求计划
     * @return 结果
     */
    public int insertTbRequirementsPlan(TbRequirementsPlan tbRequirementsPlan);

    /**
     * 修改需求计划
     * 
     * @param tbRequirementsPlan 需求计划
     * @return 结果
     */
    public int updateTbRequirementsPlan(TbRequirementsPlan tbRequirementsPlan);

    /**
     * 删除需求计划
     * 
     * @param id 需求计划ID
     * @return 结果
     */
    public int deleteTbRequirementsPlanById(Integer id);

    /**
     * 批量删除需求计划
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbRequirementsPlanByIds(String[] ids);
}
