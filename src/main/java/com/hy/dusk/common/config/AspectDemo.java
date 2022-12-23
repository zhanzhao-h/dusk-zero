//package com.hy.dusk.common.config;
//
//import com.alibaba.fastjson.JSON;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @author: ZhaoZhan
// * @date: 2022-12-23 15:43
// */
//@Slf4j
//@Aspect
//@Component
//public class AspectDemo {
//    @Pointcut("execution(com.hy.dusk.controller)")
//    public void controller() {
//    }
//
//
//    @Before("controller()")
//    public void before(JoinPoint joinPoint) {
//
//        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//
//        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
//
//        log.info("Controller切面 @Before:URL = {} ", httpServletRequest.getRequestURI());
//
//    }
//
//
//    @After(value = "controller()")
//    public void after(JoinPoint joinPoint) {
//
//        log.info("Controller切面-@After");
//
//    }
//
//
//    @AfterReturning(pointcut = "controller()", returning = "result")
//    public void afterreturning(JoinPoint joinPoint, Object result) {
//
//        log.info("Controller切面-@AfterReturning:{}", JSON.toJSONString(result));
//
//    }
//}
