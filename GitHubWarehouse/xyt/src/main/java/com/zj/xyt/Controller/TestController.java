package com.zj.xyt.Controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
@RequestMapping("/test")
@Controller
public class TestController {


    @PostMapping(value = "/registerUser")
    @ResponseBody
    public Map<String, Object> registerUser(HttpServletRequest request) throws Exception{
        Map<String, Object> map = new HashMap<>();
        //获取页面输入的新旧密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");

        String regex = "^(?!([a-zA-Z]+|\\d+)$)[a-zA-Z\\d]{6,20}$";

        boolean matches = password.matches(regex);

        System.out.println("页面输入的用户名为:"+username);
        System.out.println("页面输入的密码为:"+password);

        return map;
    }

}
