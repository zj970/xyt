package com.zj.xyt.Controller;

import com.zj.xyt.Server.LoginService;
import com.zj.xyt.utils.UserType;
import io.swagger.annotations.Api;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
/**
 * @author zj
 * @since 2022/3/12 15:00
 */
@Controller
@Api(tags = "登录接口")
@RequestMapping
public class LoginController {
    private UserType ADMIN_LOGIN_TYPE = UserType.ADMIN;
    @Autowired
    LoginService loginService;
    @GetMapping("/main")
    public String main() throws Exception{
        return "/main";
    }

    @GetMapping("/home")
    public String home() throws Exception {
        return "system/home/homePage";
    }

    /**
     * 将url地址配置在shiro的配置文件中，设置显示成功后的页面
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/success")
    @ResponseBody
    public Map<String,Object> success(HttpSession session) throws Exception{
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        //获得权限资源
        return map;
    }

    @GetMapping(value = "/login")
    public String login() throws Exception{
        return "/login";
    }
    /**
     * post方式的login方式什么时候调用？
     * 身份认证失败的时候会自动调用
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request) throws Exception{
        System.out.println("认证失败了吧！来我这了吧");
        Map<String,Object> map = new HashMap<>();
        String exceptionName = request.getAttribute("shiroLoginFailure").toString();
        if (exceptionName!=null){
            System.out.println(exceptionName);
            System.out.println(UnknownAccountException.class.getName()+"=======================");
            if (exceptionName.equals(UnknownAccountException.class.getName())){
                map.put("code",1);
                map.put("msg","用户名不正确");
                return map;
            }else if(exceptionName.equals(IncorrectCredentialsException.class.getName())){
                map.put("code",2);
                map.put("msg","密码不正确");
                return map;
            }else if (exceptionName.equals("randomCodeError")){
                map.put("code",3);
                map.put("msg","验证码不正确");
                return map;
            }
        }
        return null;
    }

}
