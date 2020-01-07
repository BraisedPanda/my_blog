package com.braisedpanda.my.blog.web.framework.aspect;

import com.braisedpanda.my.blog.commons.model.po.GlobalLog;
import com.braisedpanda.my.blog.commons.model.po.User;
import com.braisedpanda.my.blog.commons.utils.DateUtils;
import com.braisedpanda.my.blog.commons.utils.IPUtils;
import com.braisedpanda.my.blog.web.service.GlobalLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @program: my-blog
 * @description: 统一日志管理
 * @author: chenzhen
 * @create: 2020-01-07 14:10
 **/
@Slf4j
@Aspect
@Component
public class GlobalLogAspect {
    @Autowired
    private GlobalLogService globalLogService;

    //@Pointcut 切入点
    @Pointcut("execution(* com.braisedpanda.my.blog.web.controller.*.*(..)) ")
    public void globalLogAspect() {
    }

    @Before(value = "globalLogAspect()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        GlobalLog globalLog = new GlobalLog();
        String url = request.getRequestURI(); //获取操作的URL
        String controllerUrl = joinPoint.getSignature().getDeclaringTypeName();
        int index = controllerUrl.lastIndexOf(".")+1;
        controllerUrl = controllerUrl.substring(index); //获取control路径
        String ip = IPUtils.getIpAddr(request); //获取ip地址
        String host = IPUtils.getLocalHost();  //获取host
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String username = "普通游客";
        if(user!=null){
            username = user.getUsername();
        }
        String date = DateUtils.currentStandardDate();
        globalLog.setIp(ip);
        globalLog.setUsername(username);
        globalLog.setUrl(url);
        globalLog.setController(controllerUrl);
        globalLog.setDate(date);
        globalLog.setHost(host);
        globalLogService.insert(globalLog);

    }

    @Around(value = "globalLogAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        return joinPoint.proceed();
    }

    @After(value = "globalLogAspect()")
    public void doAfter(JoinPoint joinPoint){

    }


}
