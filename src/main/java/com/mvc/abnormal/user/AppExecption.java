package com.mvc.abnormal.user;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 同统一异常处理
 */
@ControllerAdvice
public class AppExecption {
    @ExceptionHandler(UserLoginExecption.class)
    public String userExecption(){
        return "erroruser";
    }
}
