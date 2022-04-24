package com.zj.xyt.Controller;

import com.zj.xyt.Server.LoginService;
import io.swagger.annotations.Api;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zj
 * @since 2022/3/12 15:00
 */
@Controller
@Api(tags = "登录接口")
public class LoginController {
    @Autowired
    LoginService loginService;
    @GetMapping("/main")
    public String main() throws Exception{
        return "/main";
    }

    @GetMapping("/registerUser")
    public String registerForm(){
        System.out.println("registerForm");
        return "/registerForm";
    }
    @GetMapping("/home")
    public String home() throws Exception{
        System.out.println("homePage");
        return "/system/home/homePage";
    }
    @GetMapping(value = "/login")
    public String login() throws Exception{
        System.out.println("login");
        return "/login";
    }

    /**
     * post方式的login方式什么时候调用？
     * 身份认证失败的时候会自动调用
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request,Map<String, Object> map) throws Exception {
        System.out.println("认证失败了吧！来我这了吧");
        //认证失败后从request中获取shiro处理的信息
        //shiroLoginFailure:就是shiro异常类的全类名
        String exceptionName = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("--------------------------------"+exceptionName);
        if (exceptionName !=null ){
            if (exceptionName.equals(UnknownAccountException.class.getName())) {
                map.put("code", 1);
                map.put("msg", "用户名不正确");
                return map;
            } else if (exceptionName.equals(IncorrectCredentialsException.class.getName())) {
                map.put("code", 2);
                map.put("msg", "密码不正确");
                return map;
            } else if (exceptionName.equals("randomCodeError")) {
                map.put("code", 3);
                map.put("msg", "验证码不正确");
                return map;
            }
        }
        return null;
    }
}
