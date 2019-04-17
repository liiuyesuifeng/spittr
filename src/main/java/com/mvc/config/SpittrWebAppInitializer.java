package com.mvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * xml配置
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.mvc.*")//配置扫描包
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 配置请求方式
     * @return
     */
    @Override
    protected String[] getServletMappings(){
        return new String[]{"/"};
    }

    /**
     *加载contextConfigLocation配置
     *
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    /**
     * 加载DispatcherServlet
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig( new MultipartConfigElement("/tmp/spittr/uploads",2097152,2097152*2,0));
    }
//     配置 dispathservlet  filter
//    @Override
//    protected Filter[] getServletFilters() {
//        return new Filter[]{new MyFilter();
//    }
}
