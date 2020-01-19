package com.mjp.borrow.base.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

//import org.aopalliance.intercept.Joinpoint;

/**
 * <p>Description：切面类</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/18 10:25
 */
@Component
@Aspect
@Slf4j
public class WebLogger {
    @Pointcut("execution(* com.mjp.borrow.controller..*.*(..))")
    public void executeService() {
    }

    @Before("executeService()")
    public void doBeforeAdvice(JoinPoint joinPoint) {
        log.info("前置通知");
        // 获取目标方法的参数信息
        Object[] args = joinPoint.getArgs();
        // AOP代理类的信息
        Object aThis = joinPoint.getThis();
        // 代理目标对象
        Object target = joinPoint.getTarget();
        // 通知的签名
        Signature signature = joinPoint.getSignature();
        // 代理的是哪一个方法
        log.info("代理的方法{}", signature.getName());
        //AOP代理类的名字
        log.info("代理类的名字{}", signature.getDeclaringTypeName());
        log.info("AOP代理类型class信息：{}", signature.getDeclaringType());
        // 获取 requestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从requestAttributes中获取httpServletRequest信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        Enumeration<String> parameterNames = request.getParameterNames();

        Map<String, String> parameterMap = new HashMap<>(16);
        while (parameterNames.hasMoreElements()) {
            String parameter = parameterNames.nextElement();
            parameterMap.put(parameter, request.getParameter(parameter));
        }
        log.info("请求参数是：{}",parameterMap.toString());
    }

    @After("executeService()")
    public  void returnAfter(JoinPoint joinpoint){
        log.info("AOP after");
    }

}
