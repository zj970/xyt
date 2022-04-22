package com.zj.xyt.Controller;
import com.zj.xyt.Server.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    /**
     * 为客户端提供的接口
     * @param nu 账号
     * @param pd 密码
     * @return
     */
    @ApiOperation(value="查询用户", notes="通过账户密码进行验证登录")
    @GetMapping("/{nu}/{pd}")
    public String queryUser(@ApiParam(value = "账号", required = true)@PathVariable("nu") String nu, @ApiParam(value = "密码", required = true)@PathVariable("pd") String pd){
        return loginService.queryUser(nu,pd);
    }

    /**
     * 登录页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView login() throws Exception{
        return new ModelAndView("/login");
    }

    @RequestMapping("/main")
    public ModelAndView toMain() throws Exception{
        return new ModelAndView("/main");
    }
}
