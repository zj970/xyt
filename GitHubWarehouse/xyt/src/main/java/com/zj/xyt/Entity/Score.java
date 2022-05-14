package com.zj.xyt.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "com.zj.xyt.Entity.Score",description = "选课记录")
@ToString
public class Score implements Serializable {

    /**学生学号--账号*/
    @ApiModelProperty(value = "学生学号")
    private String Snu;

    /**课序号*/
    @ApiModelProperty("课序号")
    private String Lnu;

    /**成绩*/
    @ApiModelProperty("成绩")
    private Float Grade;

    /**任课老师---拓展*/
    private String Tname;
    /**任课老师---拓展*/
    private String Lname;
}
