package com.syc.config;
import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //1.创建realm需要自定义
    @Bean(name = "userRealm")//加@Bean交给spring来托管
   public UserRealm userRealm(){
        UserRealm userRealm=new UserRealm();
        //修改realm的默认凭证校验匹配器
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //设置加密算法为md5
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //设置散列次数
        hashedCredentialsMatcher.setHashIterations(1024);
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
       return  userRealm;
   }
    //2.创建安全管理器DefaultWebSecurityManager
    // web环境下不能创建SecurityManager，只能创建DefaultWebSecurityManager，------------且会自动注入给SecurityUntils
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager =new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    //3.ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager)
    {
        ShiroFilterFactoryBean bean=  new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro的内置过滤器
/**
         * anon:无序认证就可以访问
         * authc：必须认证才能访问
         * user：必须拥有了“记住我”功能才能用
         * perms：拥有对某个资源的权限才能访问
         * roles：拥有某个角色权限才能访问
         */
        Map<String,String> filterMap=new LinkedHashMap<>();
        //filterMap.put("/*","anon");//所有请求都可访问
        //添加拦截过滤的跳转连接
        filterMap.put("/toindex","authc");//管理用户
        filterMap.put("/toadduser","perms[User:adduser]");//添加用户
        filterMap.put("/userslist","perms[User:listuser]");//用户列表
        filterMap.put("/tolistadduser","perms[User:listadduser]");//用户列表
        filterMap.put("/selAllNotice","perms[admin]");//用户列表

        //添加授权
         //没有授权要跳转的页面
         bean.setUnauthorizedUrl("/to401");
        //没有认证要跳转的页面x
         bean.setLoginUrl("/to404");
         bean.setFilterChainDefinitionMap(filterMap);
         return bean;

    }
    //整合ShiroDialect，用户整合shiro和thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
     return  new ShiroDialect();
    }

}

