package com.CSI5324.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Configuration
public class LoggingAdvice {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @AfterReturning(value = "execution(* com.CSI5324.*.*.*(..))",
            returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        logger.info("{} returned with value {}", joinPoint, result);
    }

    @After(value = "execution(* com.CSI5324.*.*.*(..))")
    public void after(JoinPoint joinPoint) {
        logger.info("after execution of {}", joinPoint);
    }
}