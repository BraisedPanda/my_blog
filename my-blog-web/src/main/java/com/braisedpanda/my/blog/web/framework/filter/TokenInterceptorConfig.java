package com.braisedpanda.my.blog.web.framework.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;


/**
 * @program: my-blog
 * @description: token拦截器
 * @author: chenzhen
 * @create: 2020-01-08 10:08
 **/
@Configuration
public class TokenInterceptorConfig implements WebMvcConfigurer {

    @Bean
    TokenInterceptor tokenInterceptor(){
        return new TokenInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有控制
        registry.addInterceptor(tokenInterceptor())
                .addPathPatterns("/admin/diary/insert/**");
    }

}
