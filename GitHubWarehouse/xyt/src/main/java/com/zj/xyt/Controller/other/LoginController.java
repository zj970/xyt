package com.zj.xyt.Controller.other;

import com.zj.xyt.Entity.Admin;
import com.zj.xyt.Entity.Permission;
import com.zj.xyt.Entity.Student;
import com.zj.xyt.Entity.Teacher;
import com.zj.xyt.Server.LoginService;
import com.zj.xyt.Server.PermissionService;
import com.zj.xyt.utils.Constants;
import com.zj.xyt.utils.UserType;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author zj
 * @since 2022/3/12 15:00
 */
@Controller
@Api(tags = "登录接口")
@RequestMapping
public class LoginController {
    @Autowired
    LoginService loginService;

    @Autowired
    PermissionService permissionService;

    @GetMapping("/main")
    public String main() throws Exception{
        return "/main";
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
        //设置个人信息的展示
        Object ob = SecurityUtils.getSubject().getPrincipal();
         if(ob instanceof Admin){
             System.out.println("登录类型为管理员");
             Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
             //设置名字的
             session.setAttribute(Constants.LOGIN_USER,admin.getAnu());
         }
        if(ob instanceof Student){
            System.out.println("登录类型为学生");
            Student student = (Student) SecurityUtils.getSubject().getPrincipal();
            //设置名字的
            session.setAttribute(Constants.LOGIN_USER,student.getSname());
        }
        if(ob instanceof Teacher){

            System.out.println("登录类型为教师");
            Teacher teacher = (Teacher) SecurityUtils.getSubject().getPrincipal();
            //设置名字的
            session.setAttribute(Constants.LOGIN_USER,teacher.getTname());
        }

        List<Permission> list = permissionService.queryAll();
        session.setAttribute(Constants.LOGIN_USER_PERS,list);
        //session.getAttribute("userType");
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
