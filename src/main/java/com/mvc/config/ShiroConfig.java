package com.mvc.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import com.mvc.shior.CustomRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        return new AuthorizationAttributeSourceAdvisor();
    }

    /**
     * 安全管理器
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        return new DefaultWebSecurityManager(customRealm());
    }

    /**
     * 认证、授权
     */
    @Bean
    public CustomRealm customRealm() {
        return new CustomRealm();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager());
        shiroFilter.setLoginUrl("/unauthenticated"); // 未登录跳转url
//        shiroFilter.setSuccessUrl("/home"); // 登录成功跳转url
        shiroFilter.setUnauthorizedUrl("/unauthorized"); // 无权限跳转url
        Map<String, Filter> filters = new HashMap<>();
        filters.put("logout", logoutFilter()); // 退出过滤器
        shiroFilter.setFilters(filters);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>(); // LinkedHashMap
        filterChainDefinitionMap.put("/login", "anon"); // 登录接口配置游客权限
        filterChainDefinitionMap.put("/logout", "logout"); // 登出接口……
        filterChainDefinitionMap.put("/*", "authc"); // 全部接口配置都需要权限

        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilter;
    }

    @Bean
    public LogoutFilter logoutFilter() {
        return new LogoutFilter();
    }
}
