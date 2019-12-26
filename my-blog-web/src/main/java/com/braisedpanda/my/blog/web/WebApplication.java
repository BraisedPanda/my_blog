package com.braisedpanda.my.blog.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @program: my-blog
 * @description: web启动类
 * @author: chenzhen
 * @create: 2019-12-20 10:17
 **/
@MapperScan(basePackages = "com.braisedpanda.my.blog.web.mapper")
@SpringBootApplication
@EnableSwagger2
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class,args);
    }
}
