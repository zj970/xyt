package com.zj.xyt.Server.Impl;

import com.zj.xyt.Entity.Admin;
import com.zj.xyt.Entity.Student;
import com.zj.xyt.Entity.Teacher;
import com.zj.xyt.Mapper.LoginMapper;
import com.zj.xyt.Server.LoginService;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@Api(tags = "验证登录的业务实现层")
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginMapper loginMapper;

    @Override
    public Student queryStudent(String Snu, String Spd) {
        return loginMapper.queryStudent(Snu,Spd);
    }

    @Override
    public Teacher queryTeacher(String Tnu, String Tpd) {
        return loginMapper.queryTeacher(Tnu,Tpd);
    }

    @Override
    public Admin queryAdmin(String Anu, String Apd) {
        return loginMapper.queryAdmin(Anu,Apd);
    }

    @Override
    public String queryUser(String nu, String pd) {
        //TODO:应该写一个方法，直接判断用户输入的类型---学生or教师or管理员
        if (queryStudent(nu,pd)!=null){
            //TODO:学生账户验证成功
            return queryStudent(nu,pd).getUserType().toString();
        }else if (queryTeacher(nu,pd)!=null){
            //TODO:教师账户验证成功
            return queryTeacher(nu,pd).getUserType().toString();
        }else if (queryAdmin(nu,pd)!=null){
            //TODO：管理员账户验证成功
            return queryAdmin(nu,pd).getUserType().toString();
        }
            return "FALSE";
    }

    @Override
    public void updateStudent(String Snu, String Spd) {
        loginMapper.updateStudent(Snu,Spd);
    }

    @Override
    public void updateTeacher(String Tnu, String Tpd) {
        loginMapper.updateTeacher(Tnu, Tpd);
    }

    @Override
    public void updateAdmin(String Anu, String Apd) {
        loginMapper.updateAdmin(Anu, Apd);
    }

}
