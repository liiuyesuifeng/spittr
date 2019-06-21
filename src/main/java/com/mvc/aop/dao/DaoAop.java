package com.mvc.aop.dao;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class DaoAop {
    /**
     * 方法调用之前
     */
//    @Before("execution(* com.mvc.utils.DaoUtil.convertEntity*(object))")
    public void checkTableAndQueryTableStructure(ProceedingJoinPoint joinPoint,Object object){


    }


}
