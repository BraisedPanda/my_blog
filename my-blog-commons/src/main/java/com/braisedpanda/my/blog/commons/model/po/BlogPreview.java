package com.braisedpanda.my.blog.commons.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @program: my-blog
 * @description: 博客预览
 * @author: chenzhen
 * @create: 2019-12-24 10:43
 **/
@Data
@ApiModel(value = "博客预览")
public class BlogPreview implements Serializable{

    private static final long serialVersionUID = 5968495637537618821L;
    @Id
    @Column(name="id")
    @ApiModelProperty(value="id", example = "1")
    private Integer id;

    @Column(name="title")
    @ApiModelProperty(value="title", example = "文章标题")
    private String title;

    @Column(name="imageurl")
    @ApiModelProperty(value="imageurl", example = "文章标题")
    private String imageurl;

    @Column(name="description")
    @ApiModelProperty(value="description", example = "文章标题")
    private String description;

    @Column(name="top")
    @ApiModelProperty(value="top", example = "1")
    private Integer top;

    @Column(name="create_time")
    @ApiModelProperty(value="create_time", example = "文章标题")
    private String createTime;

    @Column(name="views")
    @ApiModelProperty(value="views", example = "1000")
    private Integer views;

    @Column(name="category")
    @ApiModelProperty(value="category", example = "博客")
    private String category;

}
