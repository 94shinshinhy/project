package com.example.project.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;

@Aspect
@Component
@Slf4j
public class DebuggingAspect {

    @Pointcut("execution(* com.example.project.service.CommentService.createComment(..))")
    private void cut(){}

    @Before("cut()")
    public void loggingArgs(JoinPoint joinPoint){

        // 입력값
        Object[] args = joinPoint.getArgs();

        // 클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        // 메소드명
        String methodName = joinPoint.getSignature()
                .getName();

        // 입력값 로깅
        for(Object obj : args){

            log.info("{}#{}#입력값 : {}", className, methodName, obj);

        }

    }

    @AfterReturning(value = "cut()", returning = "returnObj")
    public void loggingReturnValue(JoinPoint joinPoint,
                                   Object returnObj){

        // 클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        // 메소드명
        String methodName = joinPoint.getSignature()
                .getName();

        // 반환값
        log.info("{}#{}#반환값 : {}", className, methodName, returnObj);

    }

}
