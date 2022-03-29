package com.zj.xyt.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zj
 * @since 2022/3/12 15:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "管理员")
@ToString(exclude = {"Apd"})
public class Admin {
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String Anu;
    /**
     * 用户密码
     */
    @ApiModelProperty(value = "密码")
    private String Apd;
}
