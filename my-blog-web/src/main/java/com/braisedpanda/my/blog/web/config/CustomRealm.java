package com.braisedpanda.my.blog.web.config;
import com.braisedpanda.my.blog.commons.model.po.Role;
import com.braisedpanda.my.blog.commons.model.po.User;
import com.braisedpanda.my.blog.web.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import org.springframework.beans.factory.annotation.Autowired;


public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        User user = (User)principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //查询登录用户所拥有的角色，并添加角色
        int id = user.getId();
        Role role  = userService.getRole(id);
        info.addRole(role.getRole());
        return info;
    }

    //重写验证身份的方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        User user = userService.getUser(username,password);

        if (user == null) {
            throw new AccountException("用户名或密码错误");
        }

        return new SimpleAuthenticationInfo(user, password, getName());

    }



}
