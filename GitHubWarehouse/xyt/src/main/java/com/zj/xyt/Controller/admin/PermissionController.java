package com.zj.xyt.Controller.admin;

import com.zj.xyt.Entity.Permission;
import com.zj.xyt.Server.PermissionService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zj
 * @since 2022/5/14 15:00
 */
@Controller
@RequestMapping("/permission")
@Api(tags = "权限管理")
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @RequestMapping("/index")
    @RequiresPermissions("permission:query")
    public String index() throws Exception{
        return "/system/permission/index";
    }

    @RequestMapping("/parentList")
    @ResponseBody
    public List<Permission> parentList() {
        return permissionService.getParentPers();
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list() throws Exception {
        Map<String,Object> map = new HashMap<>();

        map.put("code",0);
        map.put("msg",null);
        map.put("data",permissionService.queryAll());
        return map;
    }
}
