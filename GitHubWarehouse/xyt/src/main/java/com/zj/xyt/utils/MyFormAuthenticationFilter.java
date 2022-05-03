package com.zj.xyt.utils;

import com.zj.xyt.realms.UserToken;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 工具类
 * @author zj970
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //TODO:在这里进行验证码校验
        //System.out.println("进行验证码校验");
        //1.从session 中获取正确的校验码
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        //2.取出session中的校验码
        String validateCode = (String) session.getAttribute(Constants.VALIDATE_CODE);
        //System.out.println("我是正确的验证码" + validateCode);
        //3.取出页面的验证码
        String randomCode = httpServletRequest.getParameter(Constants.RANDOM_CODE);
        //System.out.println("我是输入的验证码" + randomCode);
        //比较
        if(randomCode != null && validateCode !=null && !randomCode.equals(validateCode)){
            //如果验证失败 将验证码错误信息通过shiroLoginFailure设这到request中
            httpServletRequest.setAttribute("shiroLoginFailure",Constants.CODE_ERROR);
            //拒绝访问 不再校验用户名密码 return true
            //System.out.println("验证码錯誤" + randomCode);
            return true;
        }else
        {
            //认证通过调用父类的认证方法
            return super.onAccessDenied(request, response);
        }
    }

    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        //System.out.println("这是哪");
        WebUtils.issueRedirect(	request, response, getSuccessUrl(),null,true);
    }



    //创建多人realmd的token
    @Override
    protected AuthenticationToken createToken(String username, String password, ServletRequest request, ServletResponse response) {
        //System.out.println("-----------------request"+"登录账号为"+getUsername(request)+"登录密码为"+getPassword(request));
        String userType = request.getParameter("userType");
        //System.out.println("登录类型"+userType);
            if ("Student".equals(userType)){
                return new UserToken(username,password,"Student");
            }else if("Teacher".equals(userType)){
                return new UserToken(username,password,"Teacher");
            } else {
                return new UserToken(username,password,"Admin");
            }

    }
}
