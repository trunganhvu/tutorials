/**
 * Copyright 2024
 * Name: CheckAspect
 */
package com.anhvt.springbootaop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/16/2024
 */
@Aspect
@Component
public class CheckAspect {
    @Pointcut("execution(* com.anhvt.springbootaop.controller.IndexController.*(..))")
    public void indexControllerPointCut() {
        System.out.println("Point cut of index controller");
    }

    @Before("@annotation(com.anhvt.springbootaop.aspects.CheckSomething) " +
            "|| indexControllerPointCut()")
    public void checkSomethingBefore(JoinPoint joinPoint) {
        System.out.println("Before execution - Roles: " + Arrays.toString(getRoles(joinPoint)));
    }

    @After("@annotation(com.anhvt.springbootaop.aspects.CheckSomething)")
    public void checkSomethingAfter(JoinPoint joinPoint) {
        System.out.println("After execution - Roles: " + Arrays.toString(getRoles(joinPoint)));
    }

    @AfterReturning(pointcut = "@annotation(com.anhvt.springbootaop.aspects.CheckSomething)")
    public void checkSomethingAfterReturning(JoinPoint joinPoint) {
        System.out.println("After return execution - Roles: " + Arrays.toString(getRoles(joinPoint)));
    }

    @AfterThrowing(pointcut = "@annotation(com.anhvt.springbootaop.aspects.CheckSomething)", throwing = "ex")
    public void checkSomethingAfterThrowingAnException(JoinPoint joinPoint, Exception ex) {
        System.out.println("After throwing an exception - Roles:" + Arrays.toString(getRoles(joinPoint)) + ex);
    }

    @Around("@annotation(com.anhvt.springbootaop.aspects.CheckSomething)")
    public Object checkSomethingAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Before in Around execution. - Roles: " + Arrays.toString(getRoles(joinPoint)));
        Object result = joinPoint.proceed();
        System.out.println("After in arround execution");
        return result;
    }

    public String[] getRoles(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        CheckSomething myAnnotation = method.getAnnotation(CheckSomething.class);
        System.out.println();
        return (myAnnotation instanceof CheckSomething) ?
                myAnnotation.roles()
                : new String[0];
    }
}
