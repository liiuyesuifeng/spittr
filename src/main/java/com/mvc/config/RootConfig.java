package com.mvc.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.mvc.*",excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,value = EnableWebMvc.class)
})
@PropertySource("classpath:jdbc.properties")
public class RootConfig  {
    @Autowired
    private Environment env;
    @Bean
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        try{
            System.out.println("***********DataSource init***********");
//            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//            dataSource.setUrl("jdbc:mysql://localhost:3306/userwork?useUnicode=true&characterEncoding=utf8");
//            dataSource.setUsername("root");
//            dataSource.setPassword("sa123456");
            dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
            dataSource.setUrl(env.getProperty("jdbc.url"));
            dataSource.setUsername(env.getProperty("jdbc.username"));
            dataSource.setPassword(env.getProperty("jdbc.password"));
            dataSource.setMaxActive(30);
            dataSource.setMinIdle(5);
            dataSource.setInitialSize(5);
            dataSource.setDefaultAutoCommit(false);
        }catch (Exception e){
            System.out.println("*********DataSource*******");
            e.printStackTrace();
        }
        return dataSource;
    }
    @Bean
//    @Scope(scopeName = "protoype")
//    @Lazy(true)
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        System.out.println("***********JdbcTemplate init***********");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        if(jdbcTemplate == null){
            System.out.println("***********JdbcTemplate init error***********");
        }
        System.out.println("***********JdbcTemplate init END***********");
        return jdbcTemplate;
    }
    //开启事物
    @Bean
    public PlatformTransactionManager transactionManager(){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

}
