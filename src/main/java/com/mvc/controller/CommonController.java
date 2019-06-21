package com.mvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@RestController
public class CommonController {

    @RequestMapping("/unauthorized")
    public ModelAndView unauthorized(HttpServletResponse resp) {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("msg","权限不足");
        return  modelAndView;
    }

    @RequestMapping("/unauthenticated")
    public ModelAndView unauthenticated(HttpServletResponse resp) {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("msg","错误");
        return  modelAndView;
    }
}
