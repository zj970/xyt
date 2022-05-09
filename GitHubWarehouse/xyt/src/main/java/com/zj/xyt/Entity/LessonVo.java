package com.zj.xyt.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "课程拓展类")
public class LessonVo extends Lesson{

    /**教师姓名*/
    @ApiModelProperty(value = "教师姓名")
    private String Tname;

    /**已选课程人数*/
    @ApiModelProperty(value = "已选人数")
    private Integer choiceNum;
}
