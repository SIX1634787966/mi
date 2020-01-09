package com.mi.web.controller.business;

import com.mi.common.annotation.Log;
import com.mi.common.core.controller.BaseController;
import com.mi.common.core.domain.AjaxResult;
import com.mi.common.core.domain.Ztree;
import com.mi.common.core.page.TableDataInfo;
import com.mi.common.enums.BusinessType;
import com.mi.common.utils.poi.ExcelUtil;
import com.mi.requirementsPlan.domain.TbRequirementsPlan;
import com.mi.requirementsPlan.service.ITbRequirementsPlanService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 需求计划Controller
 * 
 * @author lhp
 * @date 2019-11-27
 */
@Controller
@RequestMapping("/requirementsPlan/requirementsPlan")
public class TbRequirementsPlanController extends BaseController
{
    private String prefix = "requirementsPlan/requirementsPlan";

    @Autowired
    private ITbRequirementsPlanService tbRequirementsPlanService;

    @RequiresPermissions("requirementsPlan:requirementsPlan:view")
    @GetMapping()
    public String requirementsPlan()
    {
        return prefix + "/requirementsPlan";
    }

    /**
     * 查询需求计划列表
     */
    @RequiresPermissions("requirementsPlan:requirementsPlan:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbRequirementsPlan tbRequirementsPlan)
    {
        startPage();
        List<TbRequirementsPlan> list = tbRequirementsPlanService.selectTbRequirementsPlanList(tbRequirementsPlan);
        return getDataTable(list);
    }

    /**
     * 导出需求计划列表
     */
    @RequiresPermissions("requirementsPlan:requirementsPlan:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbRequirementsPlan tbRequirementsPlan)
    {
        List<TbRequirementsPlan> list = tbRequirementsPlanService.selectTbRequirementsPlanList(tbRequirementsPlan);
        ExcelUtil<TbRequirementsPlan> util = new ExcelUtil<TbRequirementsPlan>(TbRequirementsPlan.class);
        return util.exportExcel(list, "requirementsPlan");
    }

    /**
     * 新增需求计划
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存需求计划
     */
    @RequiresPermissions("requirementsPlan:requirementsPlan:add")
    @Log(title = "需求计划", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbRequirementsPlan tbRequirementsPlan)
    {
        return toAjax(tbRequirementsPlanService.insertTbRequirementsPlan(tbRequirementsPlan));
    }

    /**
     * 修改需求计划
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        TbRequirementsPlan tbRequirementsPlan = tbRequirementsPlanService.selectTbRequirementsPlanById(id);
        mmap.put("tbRequirementsPlan", tbRequirementsPlan);
        return prefix + "/edit";
    }

    /**
     * 修改保存需求计划
     */
    @RequiresPermissions("requirementsPlan:requirementsPlan:edit")
    @Log(title = "需求计划", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbRequirementsPlan tbRequirementsPlan)
    {
        return toAjax(tbRequirementsPlanService.updateTbRequirementsPlan(tbRequirementsPlan));
    }

    /**
     * 删除需求计划
     */
    @RequiresPermissions("requirementsPlan:requirementsPlan:remove")
    @Log(title = "需求计划", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbRequirementsPlanService.deleteTbRequirementsPlanByIds(ids));
    }
    /**
     * 加载需求计划列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees=tbRequirementsPlanService.selectRequirementsPlanTree();
        return ztrees;
    }
    /**
     * 选择需求计划树
     */
    @GetMapping("/requirementsPlanTree")
    public String selectWarehouseTreeTree()
    {
        return prefix + "/requirementsPlanTree";
    }
}
