package com.mi.web.controller.business;

import com.mi.common.annotation.Log;
import com.mi.common.core.controller.BaseController;
import com.mi.common.core.domain.AjaxResult;
import com.mi.common.core.domain.Ztree;
import com.mi.common.core.page.TableDataInfo;
import com.mi.common.enums.BusinessType;
import com.mi.common.utils.poi.ExcelUtil;
import com.mi.materialsType.domain.TbMaterialsType;
import com.mi.materialsType.service.ITbMaterialsTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物资类型Controller
 * 
 * @author lhp
 * @date 2019-11-27
 */
@Controller
@RequestMapping("/materialsType/materialsType")
public class TbMaterialsTypeController extends BaseController
{
    private String prefix = "materialsType/materialsType";

    @Autowired
    private ITbMaterialsTypeService tbMaterialsTypeService;

    @RequiresPermissions("materialsType:materialsType:view")
    @GetMapping()
    public String materialsType()
    {
        return prefix + "/materialsType";
    }

    /**
     * 查询物资类型列表
     */
    @RequiresPermissions("materialsType:materialsType:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbMaterialsType tbMaterialsType)
    {
        startPage();
        List<TbMaterialsType> list = tbMaterialsTypeService.selectTbMaterialsTypeList(tbMaterialsType);
        return getDataTable(list);
    }

    /**
     * 导出物资类型列表
     */
    @RequiresPermissions("materialsType:materialsType:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbMaterialsType tbMaterialsType)
    {
        List<TbMaterialsType> list = tbMaterialsTypeService.selectTbMaterialsTypeList(tbMaterialsType);
        ExcelUtil<TbMaterialsType> util = new ExcelUtil<TbMaterialsType>(TbMaterialsType.class);
        return util.exportExcel(list, "materialsType");
    }

    /**
     * 新增物资类型
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存物资类型
     */
    @RequiresPermissions("materialsType:materialsType:add")
    @Log(title = "物资类型", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbMaterialsType tbMaterialsType)
    {
        return toAjax(tbMaterialsTypeService.insertTbMaterialsType(tbMaterialsType));
    }

    /**
     * 修改物资类型
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        TbMaterialsType tbMaterialsType = tbMaterialsTypeService.selectTbMaterialsTypeById(id);
        mmap.put("tbMaterialsType", tbMaterialsType);
        return prefix + "/edit";
    }

    /**
     * 修改保存物资类型
     */
    @RequiresPermissions("materialsType:materialsType:edit")
    @Log(title = "物资类型", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbMaterialsType tbMaterialsType)
    {
        return toAjax(tbMaterialsTypeService.updateTbMaterialsType(tbMaterialsType));
    }

    /**
     * 删除物资类型
     */
    @RequiresPermissions("materialsType:materialsType:remove")
    @Log(title = "物资类型", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbMaterialsTypeService.deleteTbMaterialsTypeByIds(ids));
    }
    /**
     * 加载物资类型列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees=tbMaterialsTypeService.selectMaterialsTypeTree();
        return ztrees;
    }
    @GetMapping("/materialsTypeTree")
    public String selectWarehouseTreeTree()
    {
        return prefix + "/materialsTypeTree";
    }
}
