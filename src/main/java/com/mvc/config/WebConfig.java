package com.mvc.config;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan("com.*")//配置扫描包
public class WebConfig extends WebMvcConfigurerAdapter{
//    @Bean
//    public ContentNegotiatingViewResolver contentNegotiatingViewResolver(SpringTemplateEngine springTemplateEngine){
//        ContentNegotiatingViewResolver viewResolvers = new ContentNegotiatingViewResolver();
//        List<ViewResolver> views = new ArrayList<>();
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        resolver.setTemplateEngine(springTemplateEngine);
//        views.add(resolver)
////        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
////        resolver.setPrefix("/WEB-INF/views/");
////        resolver.setSuffix(".jsp");
////        resolver.setOrder(1);
////        resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
////        resolver.setExposeContextBeansAsAttributes(true);
////        views.add(resolver);
////        viewResolvers.setViewResolvers(views);
//        return viewResolvers;
//    }

     /**
     * 配置 Thymeleaf 视图解析器 —— 将逻辑视图名称解析为 Thymeleaf 模板视图
     *
     * @param springTemplateEngine 模板引擎
     * @return
     */
    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine springTemplateEngine){
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(springTemplateEngine);
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    /**
     * 模板引擎 —— 处理模板并渲染结果
     *
     * @param templateResolver 模板解析器
     * @return
     */
    @Bean
    public SpringTemplateEngine springTemplateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(templateResolver);
        return springTemplateEngine;
    }

    /**
     * 模板解析器 —— 加载 Thymeleaf 模板
     *
     * @return
     */
    @Bean
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setOrder(0);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }
    /**
     * 将请求转发到Servlet
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
        configurer.enable();
    }

    /**
     * 配置消息国际化
     * 1、需要在spring配置文件中加入MessageSource配置，值定加载文件名称
     * 2、在resource目录下新建messages.properties文件，编写不同语言提示
     * 3、页面使用spring jstl标签
     * @return
     */
    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource MESSAGE_SOURCE = new ResourceBundleMessageSource();
        MESSAGE_SOURCE.setBasename("messages");
        return MESSAGE_SOURCE;
    }

    /**
     * 配置解析multipart请求视图解析器 CommonsMultipartResolver
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver(){
        return new StandardServletMultipartResolver();
    }
    @Bean
    public SimpleMappingExceptionResolver exceptionResolver() {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("org.apache.shiro.authz.UnauthorizedException", "/unauthorized");
        mappings.setProperty("org.apache.shiro.authz.UnauthenticatedException", "/unauthenticated");
        resolver.setExceptionMappings(mappings);
        return resolver;
    }
//    
//    /**
//     * 获取Jndi配置
//     * @return
//     */
//    @Bean
//    public JndiObjectFactoryBean dataSource(){
//        JndiObjectFactoryBean data = new JndiObjectFactoryBean();
//        data.setJndiName("jdbc/jndi");
//        data.setResourceRef(true);
//        data.setProxyInterface(javax.sql.DataSource.class);
//        return data;
//    }

}
