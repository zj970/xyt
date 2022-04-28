package com.zj.xyt.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "课程")
public class Lesson {
    /**课序号*/
    @ApiModelProperty("课序号")
    private String Lnu;

    /**上课时间地点*/
    @ApiModelProperty("上课时间地点")
    private String Las;

    /**课容量*/
    @ApiModelProperty("课容量")
    private Integer Lnum;

    /**教师工作证号*/
    @ApiModelProperty(value = "教师工作证号")
    private Integer Tnu;

    /**课程名字*/
    @ApiModelProperty("课程名字")
    private String Lname;

    /**该科学分*/
    @ApiModelProperty("该科学分")
    private float Lcredit;
}
