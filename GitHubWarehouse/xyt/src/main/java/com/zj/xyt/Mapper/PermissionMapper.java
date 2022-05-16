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

    /**
     * 根据角色获取权限
     */
    List<Permission> queryByID(String id);

    /**
     * 获得所有的父权限，子权限要放到父权限的children集合属性中返回
     */
    List<Permission> getParentPers();
}
