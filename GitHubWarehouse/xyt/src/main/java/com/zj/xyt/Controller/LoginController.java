package com.zj.xyt.Controller;

import com.zj.xyt.Server.LoginService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zj
 * @since 2022/3/12 15:00
 */
@RestController
@RequestMapping("/login")
@Api(tags = "登录验证接口")
public class LoginController {
    @Autowired
    LoginService loginService;
    @GetMapping("/{nu}/{pd}")
    public String queryUser(@PathVariable("nu") String nu,@PathVariable("pd") String pd){

        return loginService.queryUser(nu,pd);
    }
}
