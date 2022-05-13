package com.zj.xyt.Controller.other;

import com.zj.xyt.Entity.Admin;
import com.zj.xyt.Entity.Permission;
import com.zj.xyt.Entity.Student;
import com.zj.xyt.Entity.Teacher;
import com.zj.xyt.Server.LoginService;
import com.zj.xyt.Server.PermissionService;
import com.zj.xyt.utils.Constants;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
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

    @GetMapping("/unauth")
    public String unauth() throws Exception{
        return "/unauth";
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
        //设置个人信息的展示 0 管理员 1 学生 2 教师
        Object ob = SecurityUtils.getSubject().getPrincipal();
         if(ob instanceof Admin){
             System.out.println("登录类型为管理员");
             if (SecurityUtils.getSubject().hasRole("admin")){
                 System.out.println("=-=======================================");
             }

             Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
             //设置名字的
             session.setAttribute(Constants.LOGIN_USER,admin.getAnu());
             List<Permission> list = permissionService.queryByID("0");
             session.setAttribute(Constants.LOGIN_USER_PERS,list);
         }
        if(ob instanceof Student){
            System.out.println("登录类型为学生");
            Student student = (Student) SecurityUtils.getSubject().getPrincipal();
            //设置名字的
            session.setAttribute(Constants.LOGIN_USER,student.getSname());
            List<Permission> list = permissionService.queryByID("1");
            session.setAttribute(Constants.LOGIN_USER_PERS,list);
        }
        if(ob instanceof Teacher){

            System.out.println("登录类型为教师");
            Teacher teacher = (Teacher) SecurityUtils.getSubject().getPrincipal();
            //设置名字的
            session.setAttribute(Constants.LOGIN_USER,teacher.getTname());
            List<Permission> list = permissionService.queryByID("2");
            session.setAttribute(Constants.LOGIN_USER_PERS,list);
        }

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
    @PostMapping(value = "/login")
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request) throws Exception{
        System.out.println("认证失败了吧！来我这了吧");
        Map<String,Object> map = new HashMap<>();
        if(request.getAttribute("shiroLoginFailure")!=null) {
            String exceptionName = request.getAttribute("shiroLoginFailure").toString();
            if (exceptionName != null) {
                System.out.println(exceptionName);
                System.out.println(UnknownAccountException.class.getName() + "=======================");
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
        }
        return null;
    }
    @RequestMapping("/passwordRestIndex")
    public String passwordRestIndex(){
        return "/system/user/changePwd";
    }

    @RequestMapping(value = "/passwordRest",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> passwordRest(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<>();

        //获取页面输入的新旧密码
        String oldPassword = request.getParameter("oldPassword");
        String newPassword1 = request.getParameter("newPassword1");
        String newPassword2 = request.getParameter("newPassword2");

        String regex = "^(?!([a-zA-Z]+|\\d+)$)[a-zA-Z\\d]{6,20}$";
        String salt;
        boolean matches = newPassword1.matches(regex);

        //获取用户信息
        if (SecurityUtils.getSubject().getPrincipal() instanceof Student){
            Student student = (Student) SecurityUtils.getSubject().getPrincipal();
            salt = student.getSnu();
            //将页面输入的密码进行加密 存入oldMd5Hash
            Md5Hash oldMd5Hash = new Md5Hash(oldPassword, salt,1024);
            Md5Hash newMd5Hash = new Md5Hash(newPassword1, salt,1024);
            //进行密码的判断
            if(oldPassword.length() <= 0 || newPassword1.length() <= 0 || newPassword2.length() <= 0){
                map.put("code",4);
                map.put("msg","密码不能为空");
            }else if(!student.getSpd().equals(oldMd5Hash.toString())){
                map.put("code",2);
                map.put("msg","输入的旧密码不正确");
            }else if(!newPassword1.equals(newPassword2)){
                map.put("code",1);
                map.put("msg","输入的新密码不一致");
            }else if(!matches){
                map.put("code",3);
                map.put("msg","密码必须包含字母、数字且长度为6-20位");
            }
            else if(student.getSpd().equals(oldMd5Hash.toString())){
                loginService.updateStudent(salt,newMd5Hash.toString());
                map.put("code",0);
                map.put("msg","修改成功");
            }
        }else if (SecurityUtils.getSubject().getPrincipal() instanceof Teacher){
            Teacher teacher = (Teacher) SecurityUtils.getSubject().getPrincipal();
            salt = teacher.getTnu();
            //将页面输入的密码进行加密 存入oldMd5Hash
            Md5Hash oldMd5Hash = new Md5Hash(oldPassword, salt,1024);
            Md5Hash newMd5Hash = new Md5Hash(newPassword1, salt,1024);
            //进行密码的判断
            if(oldPassword.length() <= 0 || newPassword1.length() <= 0 || newPassword2.length() <= 0){
                map.put("code",4);
                map.put("msg","密码不能为空");
            }else if(!teacher.getTpd().equals(oldMd5Hash.toString())){
                map.put("code",2);
                map.put("msg","输入的旧密码不正确");
            }else if(!newPassword1.equals(newPassword2)){
                map.put("code",1);
                map.put("msg","输入的新密码不一致");
            }else if(!matches){
                map.put("code",3);
                map.put("msg","密码必须包含字母、数字且长度为6-20位");
            }
            else if(teacher.getTpd().equals(oldMd5Hash.toString())){
                loginService.updateTeacher(salt,newMd5Hash.toString());
                map.put("code",0);
                map.put("msg","修改成功");
            }
        }else if (SecurityUtils.getSubject().getPrincipal() instanceof Admin){
            Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
            salt = admin.getAnu();
            //将页面输入的密码进行加密 存入oldMd5Hash
            Md5Hash oldMd5Hash = new Md5Hash(oldPassword, salt);
            Md5Hash newMd5Hash = new Md5Hash(newPassword1, salt);
            //进行密码的判断
            if(oldPassword.length() <= 0 || newPassword1.length() <= 0 || newPassword2.length() <= 0){
                map.put("code",4);
                map.put("msg","密码不能为空");
            }else if(!admin.getApd().equals(oldMd5Hash.toString())){
                map.put("code",2);
                map.put("msg","输入的旧密码不正确");
            }else if(!newPassword1.equals(newPassword2)){
                map.put("code",1);
                map.put("msg","输入的新密码不一致");
            }else if(!matches){
                map.put("code",3);
                map.put("msg","密码必须包含字母、数字且长度为6-20位");
            }
            else if(admin.getApd().equals(oldMd5Hash.toString())){
                loginService.updateAdmin(salt,newMd5Hash.toString());
                map.put("code",0);
                map.put("msg","修改成功");
            }
        }

        return map;
    }
}
