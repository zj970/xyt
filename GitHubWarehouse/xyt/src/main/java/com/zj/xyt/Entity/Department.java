package com.zj.xyt.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * @author zj
 * @creatDate 2022/3/1 15:00
 */
@Data
@ApiModel(value = "系")
@NoArgsConstructor
public class Department  implements Serializable {
    /**系编号*/
    @ApiModelProperty("系编号")
    private String Dnu;

    /**系名*/
    @ApiModelProperty("系名")
    private String Dname;

    /**系电话*/
    @ApiModelProperty("系电话")
    private String Dphone;

}
