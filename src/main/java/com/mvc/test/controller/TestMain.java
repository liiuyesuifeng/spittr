package com.mvc.test.controller;
import com.mvc.config.RootConfig;
import com.mvc.config.SpittrWebAppInitializer;
import com.mvc.config.WebConfig;
import com.mvc.eitity.User;
import com.mvc.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

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

    @Test
    public void testSpringTmp(){
        User user = new User();
        user.setName("cc");
        List<User> users = userService.queryUserList(user);
        for(User u :users){
            System.out.println(u);
        }

    }

}
