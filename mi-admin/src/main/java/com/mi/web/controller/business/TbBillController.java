package com.mi.web.controller.business;

import com.alibaba.fastjson.JSON;
import com.mi.bill.domain.TbBill;
import com.mi.bill.service.ITbBillService;
import com.mi.common.annotation.Log;
import com.mi.common.core.controller.BaseController;
import com.mi.common.core.domain.AjaxResult;
import com.mi.common.core.page.TableDataInfo;
import com.mi.common.enums.BusinessType;
import com.mi.common.utils.pdf.PdfUtils;
import com.mi.common.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 凭条Controller
 * 
 * @author lhp
 * @date 2019-12-19
 */
@Controller
@RequestMapping("/bill/bill")
public class TbBillController extends BaseController
{
    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
    @Autowired
    private RedisTemplate redisTemplate;
    private String prefix = "bill/bill";

    @Autowired
    private ITbBillService tbBillService;

    @RequiresPermissions("bill:bill:view")
    @GetMapping()
    public String bill()
    {
        return prefix + "/bill";
    }

    /**
     * 查询凭条列表
     */
    @RequiresPermissions("bill:bill:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbBill tbBill)
    {
        startPage();
        List<TbBill> list = tbBillService.selectTbBillList(tbBill);
        return getDataTable(list);
    }

    /**
     * 导出凭条列表
     */
    @RequiresPermissions("bill:bill:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbBill tbBill)
    {
        List<TbBill> list = tbBillService.selectTbBillList(tbBill);
        ExcelUtil<TbBill> util = new ExcelUtil<TbBill>(TbBill.class);
        return util.exportExcel(list, "bill");
    }

    /**
     * 新增凭条
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存凭条
     */
    @RequiresPermissions("bill:bill:add")
    @Log(title = "凭条", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbBill tbBill)
    {
        return toAjax(tbBillService.insertTbBill(tbBill));
    }

    /**
     * 修改凭条
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbBill tbBill = tbBillService.selectTbBillById(id);
        mmap.put("tbBill", tbBill);
        return prefix + "/edit";
    }

    /**
     * 修改保存凭条
     */
    @RequiresPermissions("bill:bill:edit")
    @Log(title = "凭条", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbBill tbBill)
    {
        return toAjax(tbBillService.updateTbBill(tbBill));
    }

    /**
     * 删除凭条
     */
    @RequiresPermissions("bill:bill:remove")
    @Log(title = "凭条", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbBillService.deleteTbBillByIds(ids));
    }

    /**
     * 新增凭条
     */
    @GetMapping("/getBill")
    public void getBill(HttpServletResponse response, HttpServletRequest request, Long id, Integer redis, HttpSession session)
    {
        TbBill bill=null;
        if(null!=redis){
            System.out.println("从redis");
            Long billId = (Long)session.getAttribute("billId");
            Object o = redisTemplate.opsForValue().get("bill" + billId);
            String s = JSON.toJSONString(o);
            bill = JSON.parseObject(s, TbBill.class);
            session.removeAttribute("billId");
            redisTemplate.delete("bill"+billId);
        }else {
            bill = tbBillService.selectTbBillById(id);
        }

        StringBuffer context=new StringBuffer();
        switch (bill.getType()){
            case 1:
                context.append("入库凭条 编号:"+bill.getId()+"\n");
                break;
            case 2:
                context.append("出库凭条 编号:"+bill.getId()+"\n");
                break;
            case 3:
                context.append("报废凭条 编号:"+bill.getId()+"\n");
                break;
            case 0:
                context.append("退回凭条 编号:"+bill.getId()+"\n");
                break;
            default:
                context.append("未知凭条:"+bill.getId()+"\n");
                    break;
        }

        context.append("物资：")
        .append(bill.getMaterialsName()).append("\n")
        .append("数量：")
        .append(bill.getAcoumt()).append("件").append("\n")
        .append("金额：")
        .append(bill.getMoney()).append("元").append("\n")
        .append("时间：")
        .append(simpleDateFormat.format(bill.getCreateTime())).append("\n")
        .append("操作员：")
        .append(bill.getHandler()).append("\n");

        PdfUtils.exoprt(request,response,"凭条",context.toString());
    }
}
