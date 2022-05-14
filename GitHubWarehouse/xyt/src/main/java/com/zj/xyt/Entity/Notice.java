package com.zj.xyt.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "通知")
public class Notice implements Serializable {
    /**
     * 通知id
     */
    @ApiModelProperty(value = "通知id")
    private Integer id;
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;
    /**
     * 作者
     */
    @ApiModelProperty(value = "作者")
    private String author;
    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;
    /**
     * 类型 type = 1 全员可见 type = 2 教师可见  type = 3 草稿  管理员可见
     */
    @ApiModelProperty(value = "类型")
    private Integer type;
    /**
     * 发布日期
     */
    @ApiModelProperty(value = "发布日期")
    private Date releasedate;
}
