package com.braisedpanda.my.blog.commons.model.po;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * @program: my-blog
 * @description:
 * @author: chenzhen
 * @create: 2019-12-23 16:44
 **/
@Data
@Table(name = "editor")
public class Editor implements Serializable{

    private static final long serialVersionUID = -5782373197452169140L;
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "blog_id")
    private Integer blogId;
    @Column(name = "content")
    private String content;
    @Column(name = "text_content")
    private String textContent;
    @Column(name = "create_time")
    private String create_time;

}