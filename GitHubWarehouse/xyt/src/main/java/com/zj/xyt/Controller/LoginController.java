package com.zj.xyt.Controller;
import com.zj.xyt.Server.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "登录接口")
public class LoginController {
    @Autowired
    LoginService loginService;

    @ApiOperation(value="查询用户", notes="通过账户密码进行验证登录")
    @GetMapping("/{nu}/{pd}")
    public String queryUser(@ApiParam(value = "账号", required = true)@PathVariable("nu") String nu, @ApiParam(value = "密码", required = true)@PathVariable("pd") String pd){
        return loginService.queryUser(nu,pd);
    }
}
