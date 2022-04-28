package com.zj.xyt.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "选课记录")
public class SelectedCourse {

    /**成绩*/
    @ApiModelProperty("成绩")
    private float Grade;

    /**学生学号--账号*/
    @ApiModelProperty("学生学号")
    private String Snu;

    /**课序号*/
    @ApiModelProperty("课序号")
    private String Lnu;
}
