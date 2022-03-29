package com.zj.xyt.Entity;

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
    /**学生学号--账号*/
    @ApiModelProperty("学生学号")
    private Integer Snu;

    /**学生姓名*/
    @ApiModelProperty("学生姓名")
    private String Sname;

    /**学生性别(男false女true)*/
    @ApiModelProperty("学生性别")
    private boolean Ssex;

    /**学生出生日期*/
    @ApiModelProperty("出生日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date Sbirth;

    /**班级编号*/
    @ApiModelProperty("学生所属于班级班号")
    private String Cnu;

    /**学生总学分*/
    @ApiModelProperty("学生总学分")
    private float Scredit;

    /**学生入学时间*/
    @ApiModelProperty("学生入学时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date Sette;

    /**学生账号密码*/
    @ApiModelProperty("学生账号密码")
    private String Spd;

}
