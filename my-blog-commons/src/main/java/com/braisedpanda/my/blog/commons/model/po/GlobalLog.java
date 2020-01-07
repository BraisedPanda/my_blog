package com.braisedpanda.my.blog.commons.model.po;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @program: my-blog
 * @description:
 * @author: chenzhen
 * @create: 2020-01-07 14:46
 **/
@Table(name = "global_log")
@Data
public class GlobalLog implements Serializable{
    private static final long serialVersionUID = -9204155219461843537L;
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "ip")
    private String ip;
    @Column(name = "host")
    private String host;
    @Column(name = "controller")
    private String controller;
    @Column(name = "url")
    private String url;
    @Column(name = "t_date")
    private String date;


}
