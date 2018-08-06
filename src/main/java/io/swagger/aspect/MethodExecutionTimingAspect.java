package io.swagger.aspect;


import io.swagger.api.CategoryApiController;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

//@Aspect
@Configuration
@Slf4j
public class MethodExecutionTimingAspect {

//    private static final Logger log = LoggerFactory.getLogger(CategoryApiController.class);
//    //@Around("execution(* io.swagger.api.*(..))")
//    @Around("execution(* io.swagger.api..*.*(..))")
//    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
//        long startTime = System.currentTimeMillis();
//
//        joinPoint.proceed(); // processing to the execution for the metho where aspect is applied
//        long timeTaken = System.currentTimeMillis() - startTime;
//        log.info("Calling for method is {} , Time Taken by  {} ", joinPoint, joinPoint.getSignature().getName(),  timeTaken);
//
//    }
}