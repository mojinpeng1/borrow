package com.mjp.borrow.base.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p>Description：过滤器</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/17 16:25
 */

@Slf4j
@Component
public class MyFilter   implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("myFilter初始化成功");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        String servletPath = req.getServletPath();
        log.info("访问路径是："+servletPath);
//        req.setCharacterEncoding("utf-8");
        chain.doFilter(req,response);
    }

    @Override
    public void destroy() {
        log.info("myFilter已经销毁");

    }
}
