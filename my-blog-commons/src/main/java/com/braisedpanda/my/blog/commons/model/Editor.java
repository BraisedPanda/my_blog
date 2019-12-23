package com.braisedpanda.my.blog.commons.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @program: my-blog
 * @description:
 * @author: chenzhen
 * @create: 2019-12-23 16:44
 **/
@Data
@Table(name = "editor")
public class Editor {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "bid")
    private Integer bid;
    @Column(name = "context")
    private String content;
    @Column(name = "textContent")
    private String textContent;
    @Column(name = "create_time")
    private String create_time;

}