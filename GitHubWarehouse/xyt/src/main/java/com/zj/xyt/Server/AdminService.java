package com.zj.xyt.Server;

import com.zj.xyt.Entity.Admin;
import java.util.List;

/**
 *<p>
 *     管理员服务类
 *</p>
 * @author zj970
 */
public interface AdminService{
    /**
     * 全查询管理员信息
     */
    List<Admin> queryAdminList();
    /**
     * 根据Anu查询管理员账号
     * @return 返回管理员
     */
    Admin queryAdminByID(String Anu);
}
