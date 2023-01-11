package com.example.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@EnableAspectJAutoProxy
public class calculatorAspect {
    private final Logger requestLogger = LogManager.getLogger("request-logger");
    private long startTime;
    @Autowired
    private RequestsCounter requestCounter;

    @Pointcut("execution(* com.example.server.CalculatorController.*(..))")
    public void pointCutFunc(){}

    @Before("pointCutFunc()")
    public void beforeIncomingRequest(JoinPoint joinPoint) {
        startTime = System.currentTimeMillis();
        requestCounter.encreas();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes!=null){
            requestLogger.info("Incoming request | #{} | resource: {} | HTTP Verb {}", requestCounter.getCounter().toString(), requestAttributes.getRequest().getRequestURI(),requestAttributes.getRequest().getMethod());}
    }

    @After("pointCutFunc()")
    public void afterIncomingRequest(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        long duration = System.currentTimeMillis() - startTime;
        if (requestAttributes!=null){
            requestLogger.debug("request #{} duration: {}ms", requestCounter.getCounter().toString(),duration);}
    }
}
