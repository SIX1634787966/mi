package com.mi.web.controller.business;

import com.mi.common.annotation.Log;
import com.mi.common.core.controller.BaseController;
import com.mi.common.core.domain.AjaxResult;
import com.mi.common.core.domain.Ztree;
import com.mi.common.core.page.TableDataInfo;
import com.mi.common.enums.BusinessType;
import com.mi.common.utils.poi.ExcelUtil;
import com.mi.framework.util.ShiroUtils;
import com.mi.materialsBusiness.domain.Tbmaterials;
import com.mi.materialsBusiness.service.ITbmaterialsService;
import com.mi.tbPurchaseBusiness.service.ITbPurchaseService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

/**
 * 物资管理Controller
 * 
 * @author lhp
 * @date 2019-11-12
 */
@Controller
@RequestMapping("/materialsBusiness/materialsBusiness")
public class TbmaterialsController extends BaseController
{
//    @Autowired
//    RabbitAdmin rabbitAdmin;
    @Autowired
    RabbitTemplate template;

    private String prefix = "materialsBusiness/materialsBusiness";

    @Autowired
    private ITbmaterialsService tbmaterialsService;
    @Autowired
    ITbPurchaseService tbPurchaseService;
    @RequiresPermissions("materialsBusiness:materialsBusiness:view")
    @GetMapping()
    public String materialsBusiness()
    {
        return prefix + "/materialsBusiness";
    }
    @RequiresPermissions("materialsBusiness:materialsBusiness:view")
    @GetMapping("/inventory")
    public String inventory()
    {
        return prefix + "/inventory";
    }

    /**
     * 查询物资管理列表
     */
    @RequiresPermissions("materialsBusiness:materialsBusiness:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Tbmaterials tbmaterials)
    {
        startPage();
        List<Tbmaterials> list = tbmaterialsService.selectTbmaterialsList(tbmaterials);
        return getDataTable(list);
    }

    /**
     * 导出物资管理列表
     */
    @RequiresPermissions("materialsBusiness:materialsBusiness:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Tbmaterials tbmaterials)
    {
        List<Tbmaterials> list = tbmaterialsService.selectTbmaterialsList(tbmaterials);
        ExcelUtil<Tbmaterials> util = new ExcelUtil<Tbmaterials>(Tbmaterials.class);
        return util.exportExcel(list, "物资数据");
    }

    /**
     * 新增物资管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存物资管理
     */
    @RequiresPermissions("materialsBusiness:materialsBusiness:add")
    @Log(title = "物资管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Tbmaterials tbmaterials)
    {
        return toAjax(tbmaterialsService.insertTbmaterials(tbmaterials));
    }

    /**
     * 修改物资管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {

        Tbmaterials tbmaterials = tbmaterialsService.selectTbmaterialsById(id);
        mmap.put("tbmaterials", tbmaterials);
        return prefix + "/edit";
    }

    /**
     * 修改保存物资管理
     */
    @RequiresPermissions("materialsBusiness:materialsBusiness:edit")
    @Log(title = "物资管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Tbmaterials tbmaterials)
    {
        return toAjax(tbmaterialsService.updateTbmaterials(tbmaterials));
    }

    /**
     * 删除物资管理
     */
    @RequiresPermissions("materialsBusiness:materialsBusiness:remove")
    @Log(title = "物资管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbmaterialsService.deleteTbmaterialsByIds(ids));

    }
    @RequiresPermissions("materialsBusiness:materialsBusiness:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap mmap)
    {

        mmap.put("name", "materials");
        mmap.put("materials", tbmaterialsService.selectTbmaterialsById(id));
        return prefix + "/detail";
    }

    /**
     * 导入物资信息
     * @param file
     * @param updateSupport
     * @return
     * @throws Exception
     */
    @Log(title = "物资管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("materialsBusiness:materialsBusiness:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<Tbmaterials> util = new ExcelUtil<Tbmaterials>(Tbmaterials.class);
        List<Tbmaterials> TbmaterialsList = util.importExcel(file.getInputStream());
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = tbmaterialsService.importMaterials(TbmaterialsList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @RequiresPermissions("materialsBusiness:materialsBusiness::view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<Tbmaterials> util = new ExcelUtil<Tbmaterials>(Tbmaterials.class);
        return util.importTemplateExcel("物资数据");
    }
    /**
     * 加载物资列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees=tbmaterialsService.selectMaterialsTree();
        return ztrees;
    }
    /**
     * 选择仓库树
     */
    @GetMapping("/warehouseTree")
    public String selectWarehouseTreeTree()
    {
        return prefix + "/warehouseTree";
    }

    /**
     * 出库
     * @param tbmaterials
     * @return
     */
    @RequiresPermissions("materialsBusiness:materialsBusiness:delivery")
    @Log(title = "出库", businessType = BusinessType.UPDATE)
    @PostMapping("/delivery")
    @ResponseBody
    public AjaxResult delivery(Tbmaterials tbmaterials, Integer type, HttpSession session)
    {
       if( tbmaterialsService.updateAmountById(tbmaterials,ShiroUtils.getSysUser(),type,session)>0){
           return toAjax(1);
       }else {
           return toAjax(0);
       }

    }
    @GetMapping("/test")
    @ResponseBody
    public int test()
    {
        Tbmaterials t=new Tbmaterials();
//        for (Integer i = 0; i < 1000; i++) {
//            t.setWarehouseId(i%5);
//            t.setStyleNumber("six"+i);
//            if(i%5==1){
//                t.setName("小米"+i);
//            }
//            if(i%5==2){
//                t.setName("HUAWEI"+i);
//            }
//            if(i%5==3){
//                t.setName("OPPO"+i);
//            }
            t.setOriginSource("中国");
            t.setSpecification("规格");
            t.setUnit(0L);
//            t.setQuality(i.doubleValue());
//            t.setPutPrice(i.longValue());
//            t.setDeliveryPrice(i.longValue()+50L);
            t.setCreateTime(new Date(System.currentTimeMillis()));
           // tbmaterialsService.insertTbmaterials(t);
//            redisTemplate.opsForValue().set("1233",t);
//        System.err.println(redisTemplate.opsForValue().get("123"));
//        }
        return 0;
    }
}
