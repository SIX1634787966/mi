package com.mi.web.controller.business;

import com.mi.Mq.MailMsg;
import com.mi.common.annotation.Log;
import com.mi.common.core.controller.BaseController;
import com.mi.common.core.domain.AjaxResult;
import com.mi.common.core.page.TableDataInfo;
import com.mi.common.enums.BusinessType;
import com.mi.common.utils.poi.ExcelUtil;
import com.mi.framework.util.ShiroUtils;
import com.mi.system.domain.SysUser;
import com.mi.system.mapper.SysUserMapper;
import com.mi.tbPurchaseBusiness.domain.TbPurchase;
import com.mi.tbPurchaseBusiness.service.ITbPurchaseService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 采购计划Controller
 * 
 * @author lhp
 * @date 2019-11-13
 */
@Controller
@RequestMapping("/tbPurchaseBusiness/tbPurchaseBusiness")
public class TbPurchaseController extends BaseController
{
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Value("${spring.mail.username}")
    private String sender;
    @Autowired
    private SysUserMapper sysUserMapper;
    private String prefix = "tbPurchaseBusiness/tbPurchaseBusiness";

    @Autowired
    private ITbPurchaseService tbPurchaseService;

    @RequiresPermissions("tbPurchaseBusiness:tbPurchaseBusiness:view")
    @GetMapping()
    public String tbPurchaseBusiness()
    {
        return prefix + "/tbPurchaseBusiness";
    }

    /**
     * 查询采购计划列表
     */
    @RequiresPermissions("tbPurchaseBusiness:tbPurchaseBusiness:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbPurchase tbPurchase)
    {
        startPage();
        List<TbPurchase> list = tbPurchaseService.selectTbPurchaseList(tbPurchase);
        return getDataTable(list);
    }

    /**
     * 导出采购计划列表
     */
    @RequiresPermissions("tbPurchaseBusiness:tbPurchaseBusiness:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbPurchase tbPurchase)
    {
        List<TbPurchase> list = tbPurchaseService.selectTbPurchaseList(tbPurchase);
        ExcelUtil<TbPurchase> util = new ExcelUtil<TbPurchase>(TbPurchase.class);
        return util.exportExcel(list, "采购数据");
    }

    /**
     * 新增采购计划
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存采购计划
     */
    @RequiresPermissions("tbPurchaseBusiness:tbPurchaseBusiness:add")
    @Log(title = "采购计划", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbPurchase tbPurchase)
    {
        int i = tbPurchaseService.insertTbPurchase(tbPurchase);
        if(i>0) {
            Integer userId = tbPurchase.getUserId();
            SysUser sysUser = sysUserMapper.selectUserById(Long.valueOf(userId));
            MailMsg mailMsg=new MailMsg();
            mailMsg.setFrom(sender);
            mailMsg.setTo(sysUser.getEmail());
            mailMsg.setSubject("工作通知");
            mailMsg.setText(sysUser.getUserName()+"，你好，\n现在公司因业务需求需要你外出采购物资，详情请登录公司物资管理系统查看");
            rabbitTemplate.convertAndSend("mail","mail",mailMsg);

        }


        return toAjax(i);
    }

    /**
     * 修改采购计划
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        TbPurchase tbPurchase = tbPurchaseService.selectTbPurchaseById(id);
        mmap.put("tbPurchase", tbPurchase);
        return prefix + "/edit";
    }

    /**
     * 修改保存采购计划
     */
    @RequiresPermissions("tbPurchaseBusiness:tbPurchaseBusiness:edit")
    @Log(title = "采购计划", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbPurchase tbPurchase)
    {
        return toAjax(tbPurchaseService.updateTbPurchase(tbPurchase,null));
    }

    /**
     * 删除采购计划
     */
    @RequiresPermissions("tbPurchaseBusiness:tbPurchaseBusiness:remove")
    @Log(title = "采购计划", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbPurchaseService.deleteTbPurchaseByIds(ids));
    }

    /**
     * 采购任务页面
     */
    @RequiresPermissions("tbPurchaseBusiness:tbPurchaseBusinessTask:view")
    @GetMapping("/tbPurchaseBusinessTask")
    public String tbPurchaseBusinessTask()
    {
        return prefix + "/tbPurchaseBusinessTask";
    }

    /**
     * 查询采购计划列表
     */
    @RequiresPermissions("tbPurchaseBusiness:tbPurchaseBusiness:myList")
    @PostMapping("/myList")
    @ResponseBody
    public TableDataInfo MyList(TbPurchase tbPurchase)
    {
        tbPurchase.setUserId(ShiroUtils.getUserId().intValue());
        startPage();
        List<TbPurchase> list = tbPurchaseService.selectTbPurchaseList(tbPurchase);
        return getDataTable(list);
    }

    /**
     * 验收，退回状态更新
     */
    @RequiresPermissions("tbPurchaseBusiness:tbPurchaseBusiness:accept")
    @PostMapping("/accept")
    @Log(title = "采购计划", businessType = BusinessType.UPDATE)
    @ResponseBody
    public AjaxResult accept(Integer id, Integer status, Integer materialsId, Integer amount, Integer planId, HttpSession session)
    {
        int res=tbPurchaseService.accept(id,status,materialsId,amount,planId,ShiroUtils.getSysUser(),session);
         return toAjax(res);
    }
    /**
     * 选择用户树
     */
    @GetMapping("/userTree")
    public String selectUserTree()
    {
        return prefix + "/userTree";
    }
    /**
     * 选择物资树
     */
    @GetMapping("/materialsTree")
    public String selectMaterialsTree()
    {
        return prefix + "/materialsTree";
    }
    /**
     * 选择供应商树
     */
    @GetMapping("/supplierTree")
    public String selectSupplierTree()
    {
        return prefix + "/supplierTree";
    }
}
