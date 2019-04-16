package com.mvc.controller;

import com.mvc.eitity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    private MessageSource messageSource;
    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/saveUser",method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult bindingResult){
        System.out.println("save");
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
        }
        return "user";
    }
    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public String showUser(Model model){
        System.out.println("**********show user********");
        model.addAttribute("user",new User());
        return "user";
    }


}
