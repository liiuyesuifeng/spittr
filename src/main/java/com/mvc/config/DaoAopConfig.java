package com.mvc.config;

import com.mvc.aop.dao.DaoAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


public class DaoAopConfig {
//    @Bean
    public DaoAop daoAop(){
        return new DaoAop();
    }
}
