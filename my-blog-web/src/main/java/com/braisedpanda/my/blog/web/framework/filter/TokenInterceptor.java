package com.braisedpanda.my.blog.web.framework.filter;

import com.braisedpanda.my.blog.web.config.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: my-blog
 * @description:
 * @author: chenzhen
 * @create: 2020-01-08 10:13
 **/
@Configuration
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //生成token
        String token = request.getParameter("token");
        String value = (String)redisUtils.get(token);
        if(!StringUtils.isEmpty(value) && value !=null && value.length()>0){
            redisUtils.del(token);
            log.info("处理请求成功.......");
            return true;
        }else {
            log.error("请勿重复提交表单.......");
            response.sendRedirect("/toError");
            return false;

        }
    }

}
