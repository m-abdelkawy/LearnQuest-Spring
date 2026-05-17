package com.student.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;

@Named
@Aspect
public class StudentAspect {
    private Logger LOG = LoggerFactory.getLogger(this.getClass().getSimpleName());

    /**
     * a pointcut to intercept any class method in the service package with any arguments
     * defines the joinpoint
     */
    @Pointcut("execution(* com.student.service.*.*(..))")
    public void log(){

    }

    @Before("log()")
    public void logBefore(JoinPoint jp){
        LOG.info("logged before " + jp.getSignature().getName());
    }

    @After("log()")
    public void logAfter(JoinPoint jp){
        LOG.info("logged after " + jp.getSignature().getName());
    }
}
