package com.zj.xyt.Mapper;

import com.zj.xyt.Entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zj970
 */
@Repository
@Mapper
public interface AdminMapper {
    /**
     * 查询所有的管理员账号
     * @return 返回所有管理员
     */
    List<Admin> queryAdminList();

    /**
     * 根据Anu查询管理员账号
     * @return 返回管理员
     */
    Admin queryAdminByID(String Anu);
}
