package com.example.aopdemo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
public class StudentAspect {

    /**
     * This advice runs **before** any method execution inside the `EmployeeController` class.
     * It applies to all methods (`*` means any method name) with any number of arguments (`(..)`).
     * Purpose:
     * - Logs method execution for debugging and tracking.
     * - Can be extended to include security checks, validation, etc.
     */
    @Before(value = "execution(* com.example.aopdemo.controller.StudentController.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("Request to " + joinPoint.getSignature().getName() + "Start at " + new Date());
    }

    /**
     * This advice runs **after** any method execution inside the `EmployeeController` class.
     * It applies to all methods (`*` means any method name) with any number of arguments (`(..)`).
     * Purpose:
     * - Logs method execution for debugging and tracking.
     * - Can be extended to include security checks, validation, etc.
     */

    @After(value = "execution(* com.example.aopdemo.controller.StudentController.*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        System.out.println("Request to " + joinPoint.getSignature().getName() + "Start at " + new Date());
    }

    @Before(value = "execution(* com.example.aopdemo.service.StudentService.*(..))")
    public void beforeServiceAdvice(JoinPoint joinPoint) {
        System.out.println("Request to " + joinPoint.getSignature().getName() + "Start at " + new Date());
    }

    @After(value = "execution(* com.example.aopdemo.service.StudentService.*(..))")
    public void afterServiceAdvice(JoinPoint joinPoint) {
        System.out.println("Request to " + joinPoint.getSignature().getName() + "Start at " + new Date());

    }
}
