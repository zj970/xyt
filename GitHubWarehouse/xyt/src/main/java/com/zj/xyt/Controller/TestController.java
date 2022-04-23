package com.zj.xyt.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {
    @RequestMapping("/homePage")
    public ModelAndView homePage(){
        ModelAndView mv = new ModelAndView("system/home/homePage");
        return mv;
    }

    @RequestMapping("/registerUser")
    public ModelAndView registerForm(){

        return new ModelAndView("registerForm");
    }

    @RequestMapping(value = "/registerUser",method = RequestMethod.POST)
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
