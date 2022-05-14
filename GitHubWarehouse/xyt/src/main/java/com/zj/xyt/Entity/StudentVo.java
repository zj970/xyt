package com.zj.xyt.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Student视图类--扩展类
 * @author zj970
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "学生")
@ToString(exclude = {"Spd"})
public class StudentVo extends Student{
    /**班主任姓名*/
    @ApiModelProperty("班主任姓名")
    private String Tname;
    /**学生所属班级名称*/
    @ApiModelProperty("学生所属班级名称")
    private String Cname;
    /**学生所属系名*/
    @ApiModelProperty("学生所属系名")
    private String Dname;
    /**学生所属系名*/
    @ApiModelProperty("学生选课名称")
    private String Lname;

    /**成绩*/
    @ApiModelProperty("成绩")
    private Float Grade;

}
