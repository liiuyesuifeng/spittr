package com.mvc.controller;

import com.mvc.abnormal.user.UserLoginExecption;
import com.mvc.eitity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private MessageSource messageSource;
    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/saveUser",method = RequestMethod.POST,headers = "content-type=multipart/*")
    public String saveUser(@RequestPart("profilePicture") MultipartFile file,@Valid User user, BindingResult bindingResult){
        System.out.println("save" + "上传文件名称：" + file.getName() + "上传文件大小："+ file.getSize() + "文件类型:" + file.getContentType());
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
        }
        try{
            file.transferTo(new File("D:\\test\\" + file.getName() + file.getContentType()));
        }catch (IOException ioe){
            ioe.printStackTrace();
            System.out.println("保存文件异常");
        }
        return "home";
    }
    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public String showUser(Model model){
        System.out.println("**********show user********");
        model.addAttribute("user",new User());
        return "user";
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@Valid User user, Errors errors,Model model){
        if(user.getId() == 0){
            throw new UserLoginExecption();
        }
        model.addAttribute("user",user);
        return "userShow";
    }


}
