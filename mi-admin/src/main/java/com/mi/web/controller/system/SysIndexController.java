package com.mi.web.controller.system;

import com.mi.BulletinBusiness.service.BulletinService;
import com.mi.common.config.Global;
import com.mi.common.core.controller.BaseController;
import com.mi.framework.util.ShiroUtils;
import com.mi.system.domain.SysMenu;
import com.mi.system.domain.SysUser;
import com.mi.system.service.ISysMenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 首页 业务处理
 * 
 * @author mi
 */
@Controller
public class SysIndexController extends BaseController
{
    @Autowired
    private ISysMenuService menuService;
    @Autowired
    BulletinService bulletinService;
    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        SysUser user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("copyrightYear", Global.getCopyrightYear());
        mmap.put("demoEnabled", Global.isDemoEnabled());
        return "index";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", Global.getVersion());
        return "main";
    }
    // 简报
    @RequiresPermissions("system:bulletin:view")
    @GetMapping("/system/bulletin")
    public String bulletin(ModelMap mmap)
    {
        mmap.put("version", Global.getVersion());
        return "bulletin";
    }

    @GetMapping("/bulletin")
    @ResponseBody
    public String getBulletin()
    {
        return bulletinService.LastThirtyDaysProfitCount();
    }
}
