package com.zj.xyt.Mapper;

import com.zj.xyt.Entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper {
    /**
     * 获取所有的权限
     */
    List<Permission> queryAll();
}
