package com.zj.xyt.Entity;

import com.zj.xyt.utils.UserType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

/**
 * @author zj
 * @creatDate 2022/1/12 15:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "学生")
@ToString(exclude = {"Spd"})
public class Student {
    private UserType userType = UserType.STUDENT;

    /**学生学号--账号*/
    @ApiModelProperty(value = "学生学号")
    private String Snu;

    /**学生姓名*/
    @ApiModelProperty(value = "学生姓名")
    private String Sname;

    /**学生性别*/
    @ApiModelProperty(value = "学生性别")
    private String Ssex;

    /**学生出生日期*/
    @ApiModelProperty(value = "出生日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date Sbirth;

    /**班级编号*/
    @ApiModelProperty(value = "学生所属于班级班号")
    private String Cnu;

    /**学生总学分*/
    @ApiModelProperty(value = "学生总学分")
    private float Scredit;

    /**学生入学时间*/
    @ApiModelProperty(value = "学生入学时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date Sette;

    /**学生账号密码*/
    @ApiModelProperty(value = "学生账号密码")
    private String Spd;

}
