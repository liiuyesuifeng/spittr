package com.mvc.controller;

import com.mvc.eitity.User;
import com.mvc.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam("uid") String uid, @RequestParam("passWord") String pwd) {
        ModelAndView model = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(uid, pwd);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            model.setViewName("error");
            model.addObject("mssage","用户名或密码错误");
            return model;
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            model.setViewName("error");
            model.addObject("mssage","用户名或密码错误");
            return model;
        }
        User user = userService.queryUserById(uid);
        subject.getSession().setAttribute("user", user);
        return new ModelAndView("success");
    }
}
