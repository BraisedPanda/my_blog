package com.braisedpanda.my.blog.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: my-blog
 * @description: web启动类
 * @author: chenzhen
 * @create: 2019-12-20 10:17
 **/
@SpringBootApplication
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class,args);
    }
}
