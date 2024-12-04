package com.springInt.spring_demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {
    //&& !target(hello.hellospring.SpringConfig)" this is prevent circular references
    @Around("execution(* com.springInt..*(..)) && !target(com.springInt.SpringConfig)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.print("START: " + joinPoint.toString());
        try{
            Object result = joinPoint.proceed();
            return result;
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.print("END: " + joinPoint.toString() + " " + timeMs + " ms");
        }
    }
}
