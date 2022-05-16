package com.zj.xyt.Server.Impl;

import com.zj.xyt.Entity.Permission;
import com.zj.xyt.Mapper.PermissionMapper;
import com.zj.xyt.Server.PermissionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Api(tags = "权限的业务实现层")
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<Permission> queryAll() {
        return permissionMapper.queryAll();
    }

    @Override
    public List<Permission> queryByID(String id) {
        return permissionMapper.queryByID(id);
    }

    @Override
    public List<Permission> getParentPers() {
        return permissionMapper.getParentPers();
    }


}
