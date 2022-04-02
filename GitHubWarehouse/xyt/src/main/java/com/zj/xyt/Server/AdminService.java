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
    List<Admin> selectAdminList();

}
