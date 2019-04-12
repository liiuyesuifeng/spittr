package com.mvc.test.controller;


import com.mvc.controller.HomeController;
import com.mvc.eitity.Spittle;
import com.mvc.service.SpittleRepository;
import com.mvc.service.impl.SpittleRepositoryImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


public class HomeControllerTest extends TestMain{

    @Test
    public void testHomePage() throws Exception{
        HomeController home = new HomeController();
        //构建mockMcv
        MockMvc mockMvc = standaloneSetup(home).build();
        //使用get请求访问控制器，并校验返回视图名称
        mockMvc.perform(get("/")).andExpect(view().name("home"));
    }
    @Test
    public void testSpitters() throws Exception{
        SpittleRepository spittleRepository = new SpittleRepositoryImpl();
        HomeController home = new HomeController();
        home.setSpittleRepository(spittleRepository);
        MockMvc mockMvc = standaloneSetup(home).setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp")).build();

        mockMvc.perform(get("/home/spittles"))
                .andExpect(view().name("spittles"))
                //判断model中是否存在key：spittleList
                .andExpect(model().attributeExists("spittleList"));
    }
    @Test
    public void testSpittersParam() throws Exception{
        SpittleRepository spittleRepository = new SpittleRepositoryImpl();
        HomeController home = new HomeController();
        home.setSpittleRepository(spittleRepository);
        MockMvc mockMvc = standaloneSetup(home).setSingleView(new InternalResourceView("/WEB-INF/views/spittlesParam.jsp")).build();
        mockMvc.perform(get("/home/spittlesParam?max=1024&count50"))
                .andExpect(view().name("spittlesParam"))
                .andExpect(model().attributeExists("spittleList"));
    }
    @Test
    public void testSpittersFrom() throws Exception{

        SpittleRepository spittleRepository = new SpittleRepositoryImpl();
        HomeController home = new HomeController();
        home.setSpittleRepository(spittleRepository);
        MockMvc mockMvc = standaloneSetup(home).build();
        //post请求模型驱动，并添加模型属性，属性名称和实体属性相同
        mockMvc.perform(post("/home/spittlesFrom")
                        .param("latitude","3.1")
                        .param("context","a"))
                //判断返回是否为转发路径
                .andExpect(redirectedUrl("/home/test"));
    }




}
