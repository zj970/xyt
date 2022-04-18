package com.zj.xyt.utils;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

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
        System.out.println("进行验证码校验");
        //1.从session 中获取正确的校验码
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        //2.取出session中的校验码
        //String verificationCode = session.getAttribute();
        return super.onAccessDenied(request, response);
    }
}
