package com.zj.xyt.Entity;

import com.zj.xyt.utils.UserType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;

/**
 * @author zj
 * @since 2022/3/12 15:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "com.zj.xyt.Entity.Admin",description = "管理员实体类")
@ToString(exclude = {"Apd"})
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;
    private UserType userType = UserType.ADMIN;
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
