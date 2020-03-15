package com.ele.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Shiro配置类
 *
 * @Author dongwf
 * @Date 2019/10/8
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(
            @Qualifier("securityManager") DefaultWebSecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        /**
         * 设置安全管理器
         */
        shiroFilterFactoryBean.setSecurityManager(securityManager);

//        Map<String,String> filterMap = new LinkedHashMap<String,String>();
        /**
         * 常用过滤器
         * anon	无需认证（登录）可以访问
         * authc 必须认证才能访问
         * user  如果使用rememberMe的功能直接访问
         * perms 该资源必须得到访问权限才能访问
         * role  该资源必须得到角色权限才能访问
         */
//        filterMap.put("/test","anon"); // 放行
//        filterMap.put("/login","anon"); // 放行登录请求

        // 授权才能访问,会跳转到未授权页面
//        filterMap.put("/add", "perms[user:add]");


//		filterMap.put("/add", "authc");
//		filterMap.put("/update", "authc");

//        filterMap.put("/*", "authc"); // 拦截，都需要登录认证


        /*
         * 修改跳转的登录页面
//         */
//        shiroFilterFactoryBean.setLoginUrl("/toLogin");
//        // 设置未授权跳转的页面
//        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     *
     * @param empRealm
     * @return
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("empRealm") EmpRealm empRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置管理Realm
        securityManager.setRealm(empRealm);
        return securityManager;
    }


    /**
     * 创建Realm
     */
    @Bean(name = "empRealm")
    public EmpRealm getRealm() {
        return new EmpRealm();
    }

    /**
     * 配置thymeleaf和shiro标签组合使用
     *
     * @return
     */
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }

}
