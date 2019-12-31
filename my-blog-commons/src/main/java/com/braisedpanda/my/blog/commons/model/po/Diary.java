package com.braisedpanda.my.blog.commons.model.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

/**
 * @program: my-blog
 * @description:
 * @author: chenzhen
 * @create: 2019-12-31 14:23
 **/
@Data
@Table(name = "diary")
public class Diary implements Serializable{
    private static final long serialVersionUID = -294821374989284227L;
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "createTime")
    private String createTime;
    @Column(name = "content")
    private String content;
    @Column(name = "username")
    private String username;
    @Column(name = "weather")
    private String weather;
    @Column(name = "mood")
    private String mood;
    @Column(name = "title")
    private String title;
}
