package com.zj.xyt.Mapper;


import com.zj.xyt.Entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//这个注解表示了这是一个mybatis的mapper类 类：dao @Repository
@Mapper
@Repository
public interface AdminMapper {
    //接口
    List<Admin> queryAdminList();//查询所有用户

    Admin queryAdminLogin(String Anu,String Apd);

    Admin queryAdminByAnu(String Anu);
}
