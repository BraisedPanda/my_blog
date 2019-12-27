package com.braisedpanda.my.blog.web.controller;

import com.braisedpanda.my.blog.commons.model.po.User;
import com.braisedpanda.my.blog.web.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;

/**
 * @program: my-blog
 * @description:
 * @author: chenzhen
 * @create: 2019-12-27 16:16
 **/
@RestController
@Api(tags = "用户类",description = "用户登录、登出等控制")
public class UserController {

    @Autowired
    private UserService userService;

    //用户登录
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ModelAndView login(String username, String password, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();


        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            modelAndView.addObject("tips","*未知账户~");
            modelAndView.setViewName("/login");
            return modelAndView;
        } catch (IncorrectCredentialsException ice) {
            modelAndView.addObject("tips","*密码不正确~");
            modelAndView.setViewName("/login");
            return modelAndView;
        } catch (LockedAccountException lae) {
            modelAndView.addObject("tips","*账户已锁定~");
            modelAndView.setViewName("/login");
            return modelAndView;
        } catch (ExcessiveAttemptsException eae) {
            modelAndView.addObject("tips","*用户名或密码错误次数过多~");
            modelAndView.setViewName("/login");
            return modelAndView;
        } catch (AuthenticationException ae) {
            modelAndView.addObject("tips","*用户名或密码不正确~");
            modelAndView.setViewName("/login");
            return modelAndView;
        }
        if (subject.isAuthenticated()) {
            User user = userService.getUser(username,password);
            session.setAttribute("user",user);
            modelAndView.setViewName("/index");
            return modelAndView;
        } else {
            token.clear();
            modelAndView.addObject("tips","*未知账户~");
            modelAndView.setViewName("/login");
            return modelAndView;
        }
    }
    @ApiOperation("跳转到登录界面")
    @PostMapping("/tologin")
    public ModelAndView tologin(){
        return new ModelAndView("/login");
    }

    //退出登录
    @ApiOperation("退出登录")
    @RequestMapping("/loginout")
    public ModelAndView quite(){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return new ModelAndView("/index");
    }

}
