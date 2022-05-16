package com.zj.xyt.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "班级")
public class Classes  implements Serializable {
    /**
     * 所属系编号
     */
    @ApiModelProperty("班级编号")
    private String Cnu;
    /**
     * 所属系编号
     */
    @ApiModelProperty("所属系编号")
    private String Dnu;
    /**
     * 班主任工作证号
     */
    @ApiModelProperty(value = "班主任工作证号")
    private String Tnu;
    /**
     * 班级名称
     */
    private String Cname;

    /**
     * 班级名称
     */
    private String Dname;

    /**
     * 班主任工作证号
     */
    @ApiModelProperty(value = "班主任工作证号")
    private String Tname;
}
