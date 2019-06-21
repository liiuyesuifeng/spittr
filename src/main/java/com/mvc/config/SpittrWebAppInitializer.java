package com.mvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;

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
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);

        FilterRegistration.Dynamic characterEncoding = servletContext.addFilter("characterEncoding", CharacterEncodingFilter.class);
        characterEncoding.setInitParameter("forceEncoding", "true");
        characterEncoding.setInitParameter("encoding", "UTF-8");
        characterEncoding.addMappingForUrlPatterns(null, true, "/*");

        FilterRegistration.Dynamic shiroFilter = servletContext.addFilter("shiroFilter", DelegatingFilterProxy.class);
        shiroFilter.setInitParameter("targetFilterLifecycle", "true"); // 设置true由servlet容器控制filter的生命周期
        shiroFilter.addMappingForUrlPatterns(null, true, "/*");

        // 静态资源处理
//        servletContext.addFilter("resourceUrlEncoding", ResourceUrlEncodingFilter.class).addMappingForUrlPatterns(null, true, "/*");
    }

//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//        ctx.register(WebConfig.class);
//        ctx.setServletContext(servletContext);
//    }
    //     配置 dispathservlet  filter
//    @Override
//    protected Filter[] getServletFilters() {
//        return new Filter[]{new MyFilter();
//    }
}
