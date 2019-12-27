package com.braisedpanda.my.blog.commons.model.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @program: my-blog
 * @description:
 * @author: chenzhen
 * @create: 2019-12-27 16:07
 **/
@Table(name="user")
@Data
public class User {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @Column(name = "image")
    private String image;
}
