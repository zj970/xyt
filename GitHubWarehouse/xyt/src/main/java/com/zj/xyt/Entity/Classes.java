package com.zj.xyt.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "班级")
public class Classes {
    /**所属系编号*/
    @ApiModelProperty("所属系编号")
    private Integer Dnu;
    /**班主任工作证号*/
    @ApiModelProperty(value = "班主任工作证号")
    private Integer Tnu;
    /**班级名称*/
    private String Cname;
    /**学生*/
    @ApiModelProperty(value = "学生")
    private List<Student> C_S_List = new ArrayList<>();
    public Classes(Integer Dnu,Integer Tnu){
        this.Dnu = Dnu;
        this.Tnu = Tnu;
    }
}
