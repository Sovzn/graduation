package com.syc.config;
import com.syc.pojo.Perm;
import com.syc.pojo.User;
import com.syc.service.PermService;
import com.syc.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 自定义的UserRealm
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Autowired
    PermService permService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了---授权方法—————————doGetAuthorizationInfo———————————————————————————");
        User user=(User) principalCollection.getPrimaryPrincipal();
        //System.out.println("当前用户-------------------"+user);
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //获取用户所有权限
        List<Perm> listPerms=permService.getPerms(user.getRid());
        //授权
        for (Perm listPerm : listPerms) {
            info.addStringPermission(listPerm.getPerms());
        }
        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了---认证方法—————————doGetAuthorizationInfo———————————————————————————");
        AuthenticationInfo authenticationInfo=null;
        UsernamePasswordToken userToken=(UsernamePasswordToken) token;
        String username=userToken.getUsername();
        User user= userService.selUserByusername(username);
        //用户名认证
        if(user==null){
            return null;//数据库中没有登录名为name的人抛出异常 UnknownAccountException
        }
        //密码认证
            authenticationInfo= new SimpleAuthenticationInfo(
                    //参数1：返回数据库中正确的用户名;
                    //参数2：返回数据库中的正确md5+salt处理后的密码（可根据用户名principal在数据库中查出，
                    //参数3：注册时处理密码的盐
                    //参数4：提供当前realm的名字，this.getName();
                    user,
                    user.getPassword(),
                    ByteSource.Util.bytes("sovzn+shiyaochang"),
                    this.getName());
       return authenticationInfo;
    }
}

