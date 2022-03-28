package com.zj.xyt.Controller;

import com.zj.xyt.Entity.Admin;
import com.zj.xyt.Mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zj
 * @since 2022/3/12 15:00
 */
@RestController
public class AdminController {
    @Autowired
    private AdminMapper adminMapper;

    /**查询所有用户*/
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
    public String queryAdminOne(@PathVariable("Anu") String Anu, @PathVariable("Apd") String Apd){
        Admin admin = adminMapper.queryAdminById(Anu,Apd);
        if (admin != null){
            System.out.println(admin);
            return "ok";
        }else{
            return "false";
        }

    }

}
