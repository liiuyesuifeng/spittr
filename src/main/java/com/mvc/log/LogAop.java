package com.mvc.log;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
public class LogAop {
    @Pointcut("execution(com.mvc.controller.*(String)) " +
            "&& args(methed)")
    public void logPointcut(String methed){}
    @Before("logPointcut(methed)")
    public void beforeLog(String methed){
        System.out.println("***********methed :" + methed + "方法调用开始*******");
    }
    @After("logPointcut(methed)")
    public void afterLog(String methed){
        System.out.println("***********methed :" + methed + "方法调用结束*******");
    }
    /**
     * 执行成功之后
     */
    @AfterReturning("logPointcut(methed)")
    public void successLog(String methed){
        System.out.println("***********methed :" + methed + "方法调用成功*******");
    }

    /**
     * 出现异常之后
     */
    @AfterThrowing("logPointcut(methed)")
    public void errorLog(String methed){
        System.out.println("***********methed :" + methed + "方法调用异常*******");
    }
}
