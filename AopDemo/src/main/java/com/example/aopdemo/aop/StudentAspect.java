package com.example.aopdemo.aop;

import com.example.aopdemo.entity.Student;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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

    /**
     * Advice that runs **after the successful execution** of `addStudent()` in `StudentService`.
     * It logs the request timestamp and confirms successful student creation.
     *
     * @param joinPoint provides method details (optional)
     * @param student the returned `Student` object from `addStudent()`
     */
    @AfterReturning(value = "execution(* com.example.aopdemo.service.StudentService.addStudent(..))", returning = "student")
    public void afterReturningAdvice(JoinPoint joinPoint, Student student) {
        System.out.println("Request to method: " + joinPoint.getSignature().getName() + " started at " + new Date());

        if (student != null) {
            System.out.println("✅ Student saved successfully with ID : " + student.getId());
        } else {
            System.out.println("⚠️ addStudent() returned null. No student record was created.");
        }
    }

    /**
     * Advice that runs **if an exception occurs** during the execution of `addStudent()` in `StudentService`.
     * It logs the method name and the error message for debugging.
     *
     * @param joinPoint provides method details (optional)
     * @param exception the exception thrown by `addStudent()`
     */
    @AfterThrowing(value = "execution(* com.example.aopdemo.service.StudentService.addStudent(..))", throwing = "exception")
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception exception) {
        System.out.println("❌ Service level Exception occurred in method: " + joinPoint.getSignature().getName());
        System.out.println("🔴 Error Message: " + exception.getMessage());
    }

    @After(value = "execution(* com.example.aopdemo.controller.StudentController.addStudent(..))")
    public void afterReturningAdvice(JoinPoint joinPoint) {
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

    /**
     * This advice wraps the execution of `addStudent()` in `StudentService`.
     * It logs method start, execution time, and handles exceptions.
     */
    @Around("execution(* com.example.aopdemo.service.StudentService.addStudent(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) {
        Object result = null;
        long startTime = System.currentTimeMillis();
        try {
            System.out.println("🚀 Starting method: " + joinPoint.getSignature().getName());
            result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - startTime;
            System.out.println("✅ Method " + joinPoint.getSignature().getName() + " executed successfully in " + executionTime + "ms.");
        } catch (Throwable exception) {
            System.out.println("❌ Exception in method: " + joinPoint.getSignature().getName());
            System.out.println("🔴 Error Message: " + exception.getMessage());
        }

        return result;
    }
}

