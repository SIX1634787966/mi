package com.mi.web.controller.system;

import com.alibaba.fastjson.JSON;
import com.mi.Mq.MailMsg;
import com.mi.common.annotation.Log;
import com.mi.common.core.controller.BaseController;
import com.mi.common.core.domain.AjaxResult;
import com.mi.common.core.page.TableDataInfo;
import com.mi.common.enums.BusinessType;
import com.mi.framework.util.ShiroUtils;
import com.mi.system.domain.SysNotice;
import com.mi.system.service.ISysNoticeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公告 信息操作处理
 * 
 * @author mi
 */
@Controller
@RequestMapping("/system/notice")
public class SysNoticeController extends BaseController
{
    private String prefix = "system/notice";

    @Autowired
    private ISysNoticeService noticeService;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequiresPermissions("system:notice:view")
    @GetMapping()
    public String notice()
    {
        return prefix + "/notice";
    }

    /**
     * 查询公告列表
     */
    @RequiresPermissions("system:notice:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysNotice notice)
    {
        startPage();
        List<SysNotice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }

    /**
     * 新增公告
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存公告
     */
    @RequiresPermissions("system:notice:add")
    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysNotice notice)
    {
        notice.setCreateBy(ShiroUtils.getLoginName());
        int i = noticeService.insertNotice(notice);
//        if(i>0){
//            MailMsg msg=new MailMsg();
//            msg.setText(notice.getNoticeContent());
//            String type=notice.getNoticeType()=="1"?"公告":"通知";
//            msg.setSubject(type+":"+notice.getNoticeTitle());
//            rabbitTemplate.convertAndSend("mail","allMail",msg);
//        }

        return toAjax(i);
    }

    /**
     * 修改公告
     */
    @GetMapping("/edit/{noticeId}")
    public String edit(@PathVariable("noticeId") Long noticeId, ModelMap mmap)
    {
        mmap.put("notice", noticeService.selectNoticeById(noticeId));
        return prefix + "/edit";
    }

    /**
     * 修改保存公告
     */
    @RequiresPermissions("system:notice:edit")
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysNotice notice)
    {
        notice.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(noticeService.updateNotice(notice));
    }

    /**
     * 删除公告
     */
    @RequiresPermissions("system:notice:remove")
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(noticeService.deleteNoticeByIds(ids));
    }
    @GetMapping("/getOneNotice")
    @ResponseBody
    public String getOneNotice(Long noticeId)
    {
        return JSON.toJSONString(noticeService.selectNoticeById(noticeId));
    }
    @GetMapping("/ten")
    @ResponseBody
    public String ten(SysNotice sysNotice)
    {
        return JSON.toJSONString(noticeService.selectNoticeList(sysNotice));
    }
    @GetMapping("/ten1")
    @ResponseBody
    public String ten1(SysNotice sysNotice)
    {
        return JSON.toJSONString(noticeService.selectNoticeList(sysNotice));
    }
}
