package com.mi.statistics.mapper;

import com.mi.statistics.domain.TbStatistics;
import java.util.List;

/**
 * 统计Mapper接口
 * 
 * @author lhp
 * @date 2019-11-29
 */
public interface TbStatisticsMapper 
{
    /**
     * 查询统计
     * 
     * @param id 统计ID
     * @return 统计
     */
    public TbStatistics selectTbStatisticsById(Long id);

    /**
     * 查询统计列表
     * 
     * @param tbStatistics 统计
     * @return 统计集合
     */
    public List<TbStatistics> selectTbStatisticsList(TbStatistics tbStatistics);

    /**
     * 新增统计
     * 
     * @param tbStatistics 统计
     * @return 结果
     */
    public int insertTbStatistics(TbStatistics tbStatistics);

    /**
     * 修改统计
     * 
     * @param tbStatistics 统计
     * @return 结果
     */
    public int updateTbStatistics(TbStatistics tbStatistics);

    /**
     * 删除统计
     * 
     * @param id 统计ID
     * @return 结果
     */
    public int deleteTbStatisticsById(Long id);

    /**
     * 批量删除统计
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbStatisticsByIds(String[] ids);
}
