package com.mvc.controller;

import com.mvc.eitity.Spittle;
import com.mvc.service.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping(value = "/home")
public class HomeController {
    private static final String MAX_LONG_AS_STRING = "10000";

    private SpittleRepository spittleRepository;
    @Autowired
    public void setSpittleRepository(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String home(){
        return "home";
    }
    @RequestMapping(value = "/spittles",method = RequestMethod.GET)
    public String spittles(Model model){
        model.addAttribute(spittleRepository.findSpittles(Long.MAX_VALUE,20));
//        .addAllAttributes("spittleList",);
        return "spittles";
    }
    @RequestMapping(value = "/spittlesParam",method = RequestMethod.GET)
    public String spittles(@RequestParam(value = "max",defaultValue = MAX_LONG_AS_STRING) long max
            ,@RequestParam(value = "count",defaultValue = "20") int count,Model model){
        model.addAttribute("spittleList",spittleRepository.findSpittles(max,count));
        return "spittlesParam";

    }

    /**
     * 模型驱动，添加模型属性校验，@Valid 为关键点启动模型中校验内容
     * Errors 紧跟@Valid 后
     * @param spittle
     * @param errors
     * @return
     */
    @RequestMapping(value = "/spittlesFrom",method = RequestMethod.POST)
    public String spittlesFrom(@Valid Spittle spittle, Errors errors){
        if(errors.hasErrors()){
            System.out.println(errors.getAllErrors());
        }
        spittleRepository.save(spittle);
        return "redirect:/home/test";
    }
}
