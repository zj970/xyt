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
 * @author zj970
 * @createDate 2022/1/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "教师")
@ToString(exclude = {"Tpd"})
public class Teacher implements Serializable {
    private UserType userType = UserType.TEACHER;

    /**教师工作证号*/
    @ApiModelProperty(value = "教师工作证号")
    private String Tnu;

    /**教师姓名*/
    @ApiModelProperty(value = "教师姓名")
    private String Tname;

    /**教师性别*/
    @ApiModelProperty(value = "教师性别")
    private String Tsex;

    /**教师职称*/
    @ApiModelProperty(value = "教师职称")
    private String Title;

    /**教师联系电话*/
    @ApiModelProperty(value = "教师联系电话")
    private  String Tphone;

    /**教师入职时间*/
    @ApiModelProperty(value = "教师入职时间")
    private String Teyte;

    /**教师离职时间*/
    @ApiModelProperty(value = "教师离职时间")
    private String Tsnte;

    /**教师账号密码*/
    @ApiModelProperty(value = "教师账号密码")
    private String Tpd;

}
