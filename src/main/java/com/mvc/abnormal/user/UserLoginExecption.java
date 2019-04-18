package com.mvc.abnormal.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "User not Found")
public class UserLoginExecption extends RuntimeException {

}
