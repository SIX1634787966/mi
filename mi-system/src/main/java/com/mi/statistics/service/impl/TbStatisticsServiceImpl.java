package com.mi.statistics.service.impl;

import com.mi.common.core.domain.AjaxResult;
import com.mi.common.core.text.Convert;
import com.mi.common.utils.DateUtils;
import com.mi.common.utils.poi.ExcelUtil;
import com.mi.statistics.domain.MaterialsDetailStatistics;
import com.mi.statistics.domain.TbStatistics;
import com.mi.statistics.mapper.MaterialsDetailStatisticsMapper;
import com.mi.statistics.mapper.TbStatisticsMapper;
import com.mi.statistics.service.ITbStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 统计Service业务层处理
 * 
 * @author lhp
 * @date 2019-11-29
 */
@Service
public class TbStatisticsServiceImpl implements ITbStatisticsService 
{
    @Autowired
    private TbStatisticsMapper tbStatisticsMapper;
    @Autowired
    MaterialsDetailStatisticsMapper materialsDetailStatisticsMapper;


    /**
     * 查询统计
     * 
     * @param id 统计ID
     * @return 统计
     */
    @Override
    public TbStatistics selectTbStatisticsById(Long id)
    {
        return tbStatisticsMapper.selectTbStatisticsById(id);
    }

    /**
     * 查询统计列表
     * 
     * @param tbStatistics 统计
     * @return 统计
     */
    @Override
    public List<TbStatistics> selectTbStatisticsList(TbStatistics tbStatistics)
    {
        return tbStatisticsMapper.selectTbStatisticsList(tbStatistics);
    }

    /**
     * 新增统计
     * 
     * @param tbStatistics 统计
     * @return 结果
     */
    @Override
    public int insertTbStatistics(TbStatistics tbStatistics)
    {
        tbStatistics.setCreateTime(DateUtils.getNowDate());
        return tbStatisticsMapper.insertTbStatistics(tbStatistics);
    }

    /**
     * 修改统计
     * 
     * @param tbStatistics 统计
     * @return 结果
     */
    @Override
    public int updateTbStatistics(TbStatistics tbStatistics)
    {
        tbStatistics.setUpdateTime(DateUtils.getNowDate());
        return tbStatisticsMapper.updateTbStatistics(tbStatistics);
    }

    /**
     * 删除统计对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTbStatisticsByIds(String ids)
    {
        return tbStatisticsMapper.deleteTbStatisticsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除统计信息
     * 
     * @param id 统计ID
     * @return 结果
     */
    public int deleteTbStatisticsById(Long id)
    {
        return tbStatisticsMapper.deleteTbStatisticsById(id);
    }

    /**
     * 导出统计数据
     * @param id 统计表编号
     * @return
     */
    @Override
    public AjaxResult statistics(Long id) {
        TbStatistics statistics = tbStatisticsMapper.selectTbStatisticsById(id);
        List<MaterialsDetailStatistics> MaterialsDetailStatisticss=null;//库存
        String msg=null;//信息类型
        ExcelUtil util=null;//Excel工具类
        if(statistics.getBusinessType()==1){
            MaterialsDetailStatisticss = materialsDetailStatisticsMapper.statistics(statistics);
            MaterialsDetailStatisticss.get(0).setBeginTime(statistics.getBeginTime());
            MaterialsDetailStatisticss.get(0).setEndTime(statistics.getEndTime());
            msg="出入库统计";
            util=new ExcelUtil(MaterialsDetailStatistics.class);
        }
        return util.exportExcel(MaterialsDetailStatisticss,msg);
    }

}
