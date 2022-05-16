package com.zj.xyt.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "权限资源")
public class Permission  implements Serializable {
    /**
     * 权限id --- 自增
     */
    @ApiModelProperty(value = "权限id")
    private String id;
    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称")
    private String text;
    /**
     * 权限类型
     */
    @ApiModelProperty(value = "权限名称")
    private String type;

    /**
     * 功能地址
     */
    @ApiModelProperty(value = "功能地址")
    private String url;
    /**
     * 权限规则
     */
    @ApiModelProperty(value = "权限规则")
    private String percode;
    /**
     * 父级编号
     */
    @ApiModelProperty(value = "父级编号")
    private String parentid;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private String sortstring;
    /**
     * 是否启用，0 不启用 1 启用
     */
    @ApiModelProperty(value = "权限名称")
    private String avaliable;
}
