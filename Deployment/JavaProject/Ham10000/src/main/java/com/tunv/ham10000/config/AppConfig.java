package com.tunv.ham10000.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;

@Configuration
public class AppConfig {

    @Autowired
    private ServletContext servletContext;

    @Bean
    public FilterRegistrationBean< CustomURLFilter > filterRegistrationBean() {
        FilterRegistrationBean < CustomURLFilter > registrationBean = new FilterRegistrationBean();
        CustomURLFilter customURLFilter = new CustomURLFilter(servletContext);

        registrationBean.setFilter(customURLFilter);
        registrationBean.addUrlPatterns("/menu_item/*");
        registrationBean.addUrlPatterns("/delete_menu/*");
        registrationBean.addUrlPatterns("/del_knowledge/*");
        registrationBean.addUrlPatterns("/knowledge/*");
        registrationBean.addUrlPatterns("/search_all");
        registrationBean.addUrlPatterns("/add_knowledge/*");
        registrationBean.addUrlPatterns("/update_knowledge/*");
        registrationBean.addUrlPatterns("/update_menu/*");
        registrationBean.addUrlPatterns("/add_sub_menu/*");
        registrationBean.addUrlPatterns("/add_top_menu/*");
        registrationBean.addUrlPatterns("/archieve_knowledge/*");
        registrationBean.addUrlPatterns("/unarchieve_knowledge/*");
        registrationBean.setOrder(1); //set precedence
        return registrationBean;
    }
}
