package com.tracker.config;


import com.tracker.config.filter.ReqResponseTracingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class SpringConfiguration {


    @Bean
    public FilterRegistrationBean<ReqResponseTracingFilter> servletRegistrationBean() {
        FilterRegistrationBean<ReqResponseTracingFilter> registrationBean = new FilterRegistrationBean<>();
        ReqResponseTracingFilter filter = new ReqResponseTracingFilter();
        registrationBean.setFilter(filter);
        registrationBean.setOrder(2);
        return registrationBean;
    }


}
