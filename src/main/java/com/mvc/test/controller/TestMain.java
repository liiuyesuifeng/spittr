package com.mvc.test.controller;
import com.mvc.config.RootConfig;
import com.mvc.config.SpittrWebAppInitializer;
import com.mvc.config.WebConfig;
import com.mvc.eitity.User;
import com.mvc.service.UserService;
import com.mvc.utils.PrintUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
//@ContextConfiguration(classes = {WebMvcConfig.class, MockDataConfig.class})
@ContextConfiguration(classes = {WebConfig.class, RootConfig.class})
public class TestMain {
    @Autowired
    private UserService userService;
//
//    @Autowired
//    private PlatformTransactionManager transactionManager;

    @Test
    public void testSpringTmp(){


    }
    @Test
    public void testSpringTransaction(){
        //测试效果
        User user = userService.queryUserById("1");
        PrintUtils.println(user);

    }

}
