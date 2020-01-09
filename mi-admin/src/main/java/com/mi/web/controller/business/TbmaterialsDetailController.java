package com.mi.web.controller.business;

import com.alibaba.fastjson.JSON;
import com.mi.common.annotation.Log;
import com.mi.common.core.controller.BaseController;
import com.mi.common.core.domain.AjaxResult;
import com.mi.common.core.page.TableDataInfo;
import com.mi.common.enums.BusinessType;
import com.mi.common.utils.poi.ExcelUtil;
import com.mi.framework.util.ShiroUtils;
import com.mi.materialsDetailBusiness.domain.TbmaterialsDetail;
import com.mi.materialsDetailBusiness.service.ITbmaterialsDetailService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物资出入库详情Controller
 * 
 * @author mi
 * @date 2019-11-19
 */
@Controller
@RequestMapping("/materialsDetailBusiness/materialsDetailBusiness")
public class TbmaterialsDetailController extends BaseController
{
    private String prefix = "materialsDetailBusiness/materialsDetailBusiness";

    @Autowired
    private ITbmaterialsDetailService tbmaterialsDetailService;

    @RequiresPermissions("materialsDetailBusiness:materialsDetailBusiness:view")
    @GetMapping()
    public String materialsDetailBusiness()
    {
        return prefix + "/materialsDetailBusiness";
    }

    /**
     * 出库
     * @return
     */
    @RequiresPermissions("materialsDetailBusiness:materialsDetailBusiness:view")
    @GetMapping("/materialsDelivery")
    public String delivery()
    {
        return prefix + "/materialsDelivery";
    }
    /**
     * 查询物资出入库详情列表
     */
    @RequiresPermissions("materialsDetailBusiness:materialsDetailBusiness:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbmaterialsDetail tbmaterialsDetail,Integer hiddenMaterialsId)
    {
        if(hiddenMaterialsId!=null){
            tbmaterialsDetail.setMaterialsId(hiddenMaterialsId);
        }
        startPage();
        List<TbmaterialsDetail> list = tbmaterialsDetailService.selectTbmaterialsDetailList(tbmaterialsDetail);
        return getDataTable(list);
    }

    /**
     * 导出物资出入库详情列表
     */
    @RequiresPermissions("materialsDetailBusiness:materialsDetailBusiness:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbmaterialsDetail tbmaterialsDetail)
    {
        List<TbmaterialsDetail> list = tbmaterialsDetailService.selectTbmaterialsDetailList(tbmaterialsDetail);
        ExcelUtil<TbmaterialsDetail> util = new ExcelUtil<TbmaterialsDetail>(TbmaterialsDetail.class);
        return util.exportExcel(list, "出入库数据");
    }

    /**
     * 新增物资出入库详情
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存物资出入库详情
     */
    @RequiresPermissions("materialsDetailBusiness:materialsDetailBusiness:add")
    @Log(title = "物资出入库详情", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbmaterialsDetail tbmaterialsDetail)
    {
        tbmaterialsDetail.setUserId(ShiroUtils.getUserId());
        return toAjax(tbmaterialsDetailService.insertTbmaterialsDetail(tbmaterialsDetail));
    }

    /**
     * 修改物资出入库详情
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbmaterialsDetail tbmaterialsDetail = tbmaterialsDetailService.selectTbmaterialsDetailById(id);
        mmap.put("tbmaterialsDetail", tbmaterialsDetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存物资出入库详情
     */
    @RequiresPermissions("materialsDetailBusiness:materialsDetailBusiness:edit")
    @Log(title = "物资出入库详情", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbmaterialsDetail tbmaterialsDetail)
    {
        return toAjax(tbmaterialsDetailService.updateTbmaterialsDetail(tbmaterialsDetail));
    }

    /**
     * 删除物资出入库详情
     */
    @RequiresPermissions("materialsDetailBusiness:materialsDetailBusiness:remove")
    @Log(title = "物资出入库详情", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)

    {
        return toAjax(tbmaterialsDetailService.deleteTbmaterialsDetailByIds(ids));
    }
    /**
     * 查询出入库详细
     */
    @RequiresPermissions("materialsDetailBusiness:materialsDetailBusiness:list")
    @GetMapping("/detail/{materialsId}")
    public String detail(@PathVariable("materialsId") Integer materialsId, ModelMap mmap)
    {
        mmap.put("materialsId", materialsId);
        return prefix + "/materialsDetailBusiness";
    }
    @GetMapping("/newTenPut")
    @ResponseBody
    public String newTenPut( Integer type)
    {
        List<TbmaterialsDetail> list=tbmaterialsDetailService.newTenPut(type);
        System.err.println(list);
        return JSON.toJSONString(list);
    }

    /**
     * 校验库存收充足
     * @param amount 出库数量
     * @param materialsId 出库商品编号
     * @return
     */
    @PostMapping("/checkAmount")
    @ResponseBody
    public boolean checkAmount(Integer amount,Integer materialsId)
    {

        return tbmaterialsDetailService.checkAmount(amount,materialsId);
    }
}
