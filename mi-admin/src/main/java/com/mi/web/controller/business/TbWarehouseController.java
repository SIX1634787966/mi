package com.mi.web.controller.business;

import com.mi.common.annotation.Log;
import com.mi.common.core.controller.BaseController;
import com.mi.common.core.domain.AjaxResult;
import com.mi.common.core.domain.Ztree;
import com.mi.common.core.page.TableDataInfo;
import com.mi.common.enums.BusinessType;
import com.mi.common.utils.poi.ExcelUtil;
import com.mi.framework.util.ShiroUtils;
import com.mi.warehouseBusiness.domain.TbWarehouse;
import com.mi.warehouseBusiness.service.ITbWarehouseService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 仓库管理Controller
 * 
 * @author lhp
 * @date 2019-11-12
 */
@Controller
@RequestMapping("/warehouseBusiness/warehouseBusiness")
public class TbWarehouseController extends BaseController
{
    private String prefix = "warehouseBusiness/warehouseBusiness";

    @Autowired
    private ITbWarehouseService tbWarehouseService;

    @RequiresPermissions("warehouseBusiness:warehouseBusiness:view")
    @GetMapping()
    public String warehouseBusiness()
    {
        return prefix + "/warehouseBusiness";
    }

    /**
     * 查询仓库管理列表
     */
    @RequiresPermissions("warehouseBusiness:warehouseBusiness:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbWarehouse tbWarehouse)
    {
        startPage();
        List<TbWarehouse> list = tbWarehouseService.selectTbWarehouseList(tbWarehouse);
        return getDataTable(list);
    }

    /**
     * 导出仓库管理列表
     */
    @RequiresPermissions("warehouseBusiness:warehouseBusiness:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbWarehouse tbWarehouse)
    {
        List<TbWarehouse> list = tbWarehouseService.selectTbWarehouseList(tbWarehouse);
        ExcelUtil<TbWarehouse> util = new ExcelUtil<TbWarehouse>(TbWarehouse.class);
        return util.exportExcel(list, "仓库数据");
    }

    /**
     * 新增仓库管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存仓库管理
     */
    @RequiresPermissions("warehouseBusiness:warehouseBusiness:add")
    @Log(title = "仓库管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbWarehouse tbWarehouse)
    {
        return toAjax(tbWarehouseService.insertTbWarehouse(tbWarehouse));
    }

    /**
     * 修改仓库管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        TbWarehouse tbWarehouse = tbWarehouseService.selectTbWarehouseById(id);
        mmap.put("tbWarehouse", tbWarehouse);
        return prefix + "/edit";
    }

    /**
     * 修改保存仓库管理
     */
    @RequiresPermissions("warehouseBusiness:warehouseBusiness:edit")
    @Log(title = "仓库管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbWarehouse tbWarehouse)
    {
        return toAjax(tbWarehouseService.updateTbWarehouse(tbWarehouse));
    }

    /**
     * 删除仓库
     */
    @RequiresPermissions("warehouseBusiness:warehouseBusiness:remove")
    @Log(title = "仓库管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbWarehouseService.deleteTbWarehouseByIds(ids));

    }
    @Log(title = "仓库管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("warehouseBusiness:warehouseBusiness:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<TbWarehouse> util = new ExcelUtil<TbWarehouse>(TbWarehouse.class);
        List<TbWarehouse> tbWarehouseList = util.importExcel(file.getInputStream());
        String operName = ShiroUtils.getSysUser().getLoginName();
       String message = tbWarehouseService.importWarehouse(tbWarehouseList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @RequiresPermissions("warehouseBusiness:warehouseBusiness:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<TbWarehouse> util = new ExcelUtil<TbWarehouse>(TbWarehouse.class);
        return util.importTemplateExcel("仓库数据");
    }
    /**
     * 加载物资列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees=tbWarehouseService.selectWarehouseTree();
        return ztrees;
    }

    @GetMapping("/test")
    @ResponseBody
    public int test()
    {
        TbWarehouse t=new TbWarehouse();
        for (Integer i = 0; i < 1000; i++) {
            if(i%5==1){
                t.setName("仓库"+i);
            }
            if(i%5==2){
                t.setName("仓库"+i);
            }
            if(i%5==3){
                t.setName("仓库"+i);
            }
            t.setAddress("北京市");
            t.setIsfull(0);
            t.setStatus(0);
            t.setPresident("李海");
            t.setPhone("18278285941");
            t.setEmail("5461264@qq.com");
            t.setArea(50D);

            tbWarehouseService.insertTbWarehouse(t);
        }
        return 0;
    }
}
