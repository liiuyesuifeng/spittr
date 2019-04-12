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
        MockMvc mockMvc = standaloneSetup(home).build();
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
        mockMvc.perform(post("/home/spittlesFrom")
                        .param("latitude","3.1")
                        .param("context","a"))
                .andExpect(redirectedUrl("/home/test"));
    }




}
