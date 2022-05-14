package com.zj.xyt.Controller.admin;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zj
 * @since 2022/5/14 15:00
 */
@Controller
@RequestMapping("/permission")
@Api(tags = "权限管理")
public class PermissionController {

    @GetMapping("/index")
    public String index(){
        return "/permission/index";
    }
}
