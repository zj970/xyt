package com.zj.xyt.Controller;

import com.zj.xyt.Server.LoginService;
import io.swagger.annotations.Api;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * @author zj
 * @since 2022/3/12 15:00
 */
@Controller
@RequestMapping("/login")
@Api(tags = "登录接口")
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping("/main")
    public String main() throws Exception{
        return "/main";
    }

    @RequestMapping("/home")
    public String home() throws Exception{
        return "/system/home/homePage";
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView login() throws Exception{
        System.out.println("认证失败了吧！来我这了吧");
        return new ModelAndView("/login");
    }

    /**
     * post方式的login方式什么时候调用？
     * 身份认证失败的时候会自动调用
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(String username,String password){
        System.out.println("认证失败了吧！来我这了吧"+username+"密碼"+password);

        return null;
    }

}
