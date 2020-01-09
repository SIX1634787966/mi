package com.mi.web.controller.business;

import com.mi.common.annotation.Log;
import com.mi.common.core.controller.BaseController;
import com.mi.common.core.domain.AjaxResult;
import com.mi.common.core.domain.Ztree;
import com.mi.common.core.page.TableDataInfo;
import com.mi.common.enums.BusinessType;
import com.mi.common.utils.poi.ExcelUtil;
import com.mi.framework.util.ShiroUtils;
import com.mi.supplierBusiness.domain.TbSupplier;
import com.mi.supplierBusiness.service.ITbSupplierService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 供应商Controller
 * 
 * @author lhp
 * @date 2019-11-12
 */
@Controller
@RequestMapping("/supplierBusiness/supplierBusiness")
public class TbSupplierController extends BaseController
{
    private String prefix = "supplierBusiness/supplierBusiness";

    @Autowired
    private ITbSupplierService tbSupplierService;

    @RequiresPermissions("supplierBusiness:supplierBusiness:view")
    @GetMapping()
    public String supplierBusiness()
    {
        return prefix + "/supplierBusiness";
    }

    /**
     * 查询供应商列表
     */
    @RequiresPermissions("supplierBusiness:supplierBusiness:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbSupplier tbSupplier)
    {
        startPage();
        List<TbSupplier> list = tbSupplierService.selectTbSupplierList(tbSupplier);
        return getDataTable(list);
    }

    /**
     * 导出供应商列表
     */
    @RequiresPermissions("supplierBusiness:supplierBusiness:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbSupplier tbSupplier)
    {
        List<TbSupplier> list = tbSupplierService.selectTbSupplierList(tbSupplier);
        ExcelUtil<TbSupplier> util = new ExcelUtil<TbSupplier>(TbSupplier.class);
        return util.exportExcel(list, "供应商数据");
    }

    /**
     * 新增供应商
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存供应商
     */
    @RequiresPermissions("supplierBusiness:supplierBusiness:add")
    @Log(title = "供应商", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbSupplier tbSupplier)
    {
        return toAjax(tbSupplierService.insertTbSupplier(tbSupplier));
    }

    /**
     * 修改供应商
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        TbSupplier tbSupplier = tbSupplierService.selectTbSupplierById(id);
        mmap.put("tbSupplier", tbSupplier);
        return prefix + "/edit";
    }

    /**
     * 修改保存供应商
     */
    @RequiresPermissions("supplierBusiness:supplierBusiness:edit")
    @Log(title = "供应商", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbSupplier tbSupplier)
    {
        return toAjax(tbSupplierService.updateTbSupplier(tbSupplier));
    }

    /**
     * 删除供应商
     */
    @RequiresPermissions("supplierBusiness:supplierBusiness:remove")
    @Log(title = "供应商", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbSupplierService.deleteTbSupplierByIds(ids));

    }
    @Log(title = "供应商管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("supplierBusiness:supplierBusiness:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<TbSupplier> util = new ExcelUtil<TbSupplier>(TbSupplier.class);
        List<TbSupplier> TbSupplierList = util.importExcel(file.getInputStream());
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = tbSupplierService.importTbSupplier(TbSupplierList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @RequiresPermissions("supplierBusiness:supplierBusiness:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<TbSupplier> util = new ExcelUtil<TbSupplier>(TbSupplier.class);
        return util.importTemplateExcel("供应商数据");
    }
    /**
     * 加载供应商列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees=tbSupplierService.selectTbSupplierTree();
        return ztrees;
    }
}
