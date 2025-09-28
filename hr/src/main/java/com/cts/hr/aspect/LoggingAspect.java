package com.cts.hr.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.cts.hr..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entering method: {}", joinPoint.getSignature());
    }

    @AfterReturning(pointcut = "execution(* com.cts.hr..*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Method {} returned with value: {}", joinPoint.getSignature(), result);
    }

//    @AfterThrowing(pointcut = "execution(* com.cts.hr..*(..))", throwing = "exception")
//    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
//        logger.error("Method {} threw an exception: {}", joinPoint.getSignature(), exception.getMessage());
//    }
}
