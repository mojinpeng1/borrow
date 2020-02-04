package com.mjp.borrow.base.config;

import com.mjp.borrow.base.inteceptor.SessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * <p>Description：拦截器配置信息</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/17 17:16
 */
@Configuration

public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Resource
    private SessionInterceptor sessionInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则，/**表示拦截所有请求
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/doc.html","/favicon.ico", "/user/login","/user/addUser","/user/uploadPic",
                        "/user/getPic",
                        "/user/checkUser",
                        "/index","/webjars/**","/swagger-resources/**","/v2/**");
        super.addInterceptors(registry);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
