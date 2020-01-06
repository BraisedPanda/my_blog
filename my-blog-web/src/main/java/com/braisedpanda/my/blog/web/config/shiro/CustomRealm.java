package com.braisedpanda.my.blog.web.config.shiro;
import com.alibaba.fastjson.JSONObject;
import com.braisedpanda.my.blog.commons.model.po.Role;
import com.braisedpanda.my.blog.commons.model.po.User;
import com.braisedpanda.my.blog.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User)principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //查询登录用户所拥有的角色，并添加角色
        int id = user.getId();
        JSONObject jsonObject = (JSONObject) redisTemplate.opsForValue().get("AuthorizationInfo"+id);

        Role role = JSONObject.toJavaObject(jsonObject,Role.class);
        if(role == null){
            role = userService.getRole(id);
//            System.out.println("================开始权限查询================");
            log.info("================开始权限查询================");
            redisTemplate.opsForValue().set("AuthorizationInfo"+id,role);
        }
        info.addRole(role.getRole());
        return info;
    }
    //重写验证身份的方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        System.out.println("================开始身份查询================");
        log.info("================开始身份查询================");
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        User user = userService.getUser(username,password);
        if (user == null) {
            throw new AccountException("用户名或密码错误");
        }
        return new SimpleAuthenticationInfo(user, password, getName());
    }
}
