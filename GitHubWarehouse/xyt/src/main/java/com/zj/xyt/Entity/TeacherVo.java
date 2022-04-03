package com.zj.xyt.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Teacher视图类--扩展类
 * @author zj970
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "教师")
@ToString(exclude = {"Tpd"})
public class TeacherVo extends Teacher{
    /**教师所属系名*/
    @ApiModelProperty("教师所属系名")
    private String Dname;
}
