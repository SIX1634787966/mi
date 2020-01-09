package com.mi.web.controller.business;

import com.mi.common.annotation.Log;
import com.mi.common.core.controller.BaseController;
import com.mi.common.core.domain.AjaxResult;
import com.mi.common.core.page.TableDataInfo;
import com.mi.common.enums.BusinessType;
import com.mi.common.utils.poi.ExcelUtil;
import com.mi.statistics.domain.TbStatistics;
import com.mi.statistics.service.ITbStatisticsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 统计Controller
 * 
 * @author lhp
 * @date 2019-11-29
 */
@Controller
@RequestMapping("/statistics/statistics")
public class TbStatisticsController extends BaseController
{
    private String prefix = "statistics/statistics";

    @Autowired
    private ITbStatisticsService tbStatisticsService;
    @RequiresPermissions("statistics:statistics:view")
    @GetMapping()
    public String statistics()
    {
        return prefix + "/statistics";
    }

    /**
     * 查询统计列表
     */
    @RequiresPermissions("statistics:statistics:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbStatistics tbStatistics)
    {
        startPage();
        List<TbStatistics> list = tbStatisticsService.selectTbStatisticsList(tbStatistics);
        return getDataTable(list);
    }

    /**
     * 导出统计列表
     */
    @RequiresPermissions("statistics:statistics:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbStatistics tbStatistics)
    {
        List<TbStatistics> list = tbStatisticsService.selectTbStatisticsList(tbStatistics);
        ExcelUtil<TbStatistics> util = new ExcelUtil<TbStatistics>(TbStatistics.class);
        return util.exportExcel(list, "统计信息");
    }

    /**
     * 新增统计
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存统计
     */
    @RequiresPermissions("statistics:statistics:add")
    @Log(title = "统计", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbStatistics tbStatistics)
    {
        return toAjax(tbStatisticsService.insertTbStatistics(tbStatistics));
    }

    /**
     * 修改统计
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbStatistics tbStatistics = tbStatisticsService.selectTbStatisticsById(id);
        mmap.put("tbStatistics", tbStatistics);
        return prefix + "/edit";
    }

    /**
     * 修改保存统计
     */
    @RequiresPermissions("statistics:statistics:edit")
    @Log(title = "统计", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbStatistics tbStatistics)
    {
        return toAjax(tbStatisticsService.updateTbStatistics(tbStatistics));
    }

    /**
     * 删除统计
     */
    @RequiresPermissions("statistics:statistics:remove")
    @Log(title = "统计", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbStatisticsService.deleteTbStatisticsByIds(ids));
    }

    /**
     * 导出统计列表
     */
    @RequiresPermissions("statistics:statistics:export")
    @PostMapping("/exportDetail")
    @ResponseBody
    public AjaxResult exportDetail(Long id)
    {

        return tbStatisticsService.statistics(id);
    }
}
