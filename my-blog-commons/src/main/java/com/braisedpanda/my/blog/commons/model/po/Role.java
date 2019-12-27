package com.braisedpanda.my.blog.commons.model.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @program: my-blog
 * @description:
 * @author: chenzhen
 * @create: 2019-12-27 16:08
 **/
@Table(name="role")
@Data
public class Role {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "role")
    private String role;

    @Column(name = "description")
    private String description;
}
