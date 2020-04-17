package com.luv2code.springdemo.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    private Logger logger = Logger.getLogger(CRMLoggingAspect.class.getName());

    @Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
    private void forControllerPackage() {}

    @Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
    private void forDaoPackage() {}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {}

    @Before("forAppFlow()")
    public void before(JoinPoint joinpoint) {

        String method = joinpoint.getSignature().toShortString();
        logger.info("@Before: calling method: " + method);
        Object[] args = joinpoint.getArgs();
        for (Object arg: args) {
            logger.info("argument is: " + arg);
        }
    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "response"
    )
    public void after(JoinPoint joinpoint, Object response) {

        String method = joinpoint.getSignature().toShortString();
        logger.info("@AfterReturning: from method: " + method);
        logger.info("Response: " + response);
    }
}
