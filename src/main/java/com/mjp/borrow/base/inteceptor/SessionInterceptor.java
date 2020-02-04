package com.mjp.borrow.base.inteceptor;


import com.mjp.borrow.base.exception.CommonException;
import com.mjp.borrow.model.AccoutInfo;
import com.mjp.borrow.utils.ControllerUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Description：session拦截器，用于拦截账号是否正式登陆</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/17 17:00
 */
@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {



    /**
      *<p>拦截于请求刚进入时，进行判断，需要boolean返回值，
     * 如果返回true将继续执行，如果返回false，将不进行执行。一般用于登录校验。</p>
      */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws CommonException {
        AccoutInfo curAccount = ControllerUtils.getCurAccount( request);
        if(curAccount == null){

            throw new CommonException("当前账号未登录!");
        }
        return true;
    }

    /**
     * 拦截于方法成功返回后，视图渲染前，可以对modelAndView进行操作。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
    }

    /**
     * 拦截于方法成功返回后，视图渲染前，可以进行成功返回的日志记录。
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
    }


}
