package com.mjp.borrow.base.config;

import com.mjp.borrow.base.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * <p>Description：过滤器配置类</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/17 16:45
 */
@Configuration
public class FilterConfig {
    @Resource
    private MyFilter myFilter;

    @Bean
    public FilterRegistrationBean registerAuthFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(myFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("myFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
