package com.zj.xyt.Server;

import com.zj.xyt.Entity.Permission;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *<p>
 *     权限业务类
 *     实现动态管理权限
 *</p>
 * @author zj970
 */
@Service
@Api(tags = "公告业务层")
public interface PermissionService {
    /**
     * 获取所有的权限信息
     */
    List<Permission> queryAll();

    /**
     * 根据角色获取权限
     */
    List<Permission> queryByID(String id);
}
