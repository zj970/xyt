package com.zj.xyt.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "com.zj.xyt.Entity.Score",description = "管理员实体类")
@ToString
public class Score {

    /**学生学号--账号*/
    @ApiModelProperty(value = "学生学号")
    private String Snu;

    /**课序号*/
    @ApiModelProperty("课序号")
    private String Lnu;

    /**成绩*/
    @ApiModelProperty("成绩")
    private Float Grade;


}
