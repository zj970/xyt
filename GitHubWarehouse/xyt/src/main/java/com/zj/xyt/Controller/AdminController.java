package com.zj.xyt.Controller;

import com.zj.xyt.Entity.Admin;
import com.zj.xyt.Mapper.AdminMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zj
 * @since 2022/3/12 15:00
 */
@RestController
@RequestMapping("/user")
//说明接口文件
@Api(value = "测试接口", tags = "管理员相关的接口", description = "用户测试接口")
public class AdminController {
    @Autowired
    private AdminMapper adminMapper;

    /**查询所有用户*/
    @ApiOperation(value = "查询所有admin账号信息",notes = "根据账号查询用户信息")
    @GetMapping("/queryAdminList")
    public List<Admin> queryAdminList(){
        List<Admin> adminList = adminMapper.queryAdminList();
        for (Admin admin:adminList){
            System.out.println(admin);
        }
        return adminList;
    }

    /**登录验证*/
    @GetMapping("/queryAdminOne/{Anu}/{Apd}")
    @ApiOperation(value = "验证登录信息",notes = "根据账号查询用户信息")
    public String queryAdminOne(@PathVariable("Anu") String Anu, @PathVariable("Apd") String Apd){
        Admin admin = adminMapper.queryAdminLogin(Anu,Apd);
        if (admin != null){
            System.out.println(admin);
            return "ok";
        }else{
            return "false";
        }
    }

    /**
     * 根据Anu查询用户
     */
    @GetMapping(value = "findByAnu")
    @ApiOperation(value = "根据Anu获取用户信息",notes = "根据账号查询用户信息")
    public Admin getAdmin(String Anu){
        return adminMapper.queryAdminByAnu(Anu);
    }

}
