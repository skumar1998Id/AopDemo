What is Spring AOP ?
Ans: AOP (Aspect-Oriented Programming) is a programming paradigm that helps separate cross-cutting concerns (like logging, security, transaction management) from the core business logic.

Instead of writing the same code in multiple places, AOP allows you to define it once and apply it dynamically to different parts of your application.

Why Use AOP?
Avoid code duplication (e.g., writing logging in every method).
Improve code maintainability by centralizing concerns.
Apply features like security, logging, and performance monitoring without modifying core logic.

 Key Terminologies in AOP:

 1. Aspect
 An Aspect is a module that contains cross-cutting concerns (like logging, security, transaction management).
 It is implemented using a Java class annotated with @Aspect.

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.example.service.*.*(..))")
    public void logBefore() {
        System.out.println("Logging before method execution...");
    }
}


2. Advice

Advice defines what action should be taken and when.
Spring AOP provides different types of advice:

@Before – Runs before method execution.
@AfterReturning – Runs after method returns successfully.
@AfterThrowing – Runs if a method throws an exception.
@After – Runs after method execution (success or failure).
@Around – Runs before and after method execution(The @Around allowed us to execute custom logic before and after the method execution and even modify the return value or exception).

3. Join Point
A Join Point is a point in the execution of the program where AOP can be applied.
In Spring AOP, a Join Point is always a method execution.
✅ Example: Every method in PaymentService is a Join Point.

public class PaymentService {
    public void makePayment() { }  // Join Point
    public void refundPayment() { }  // Join Point
}

AOP can be applied to both methods.

4.  Pointcut
A Pointcut defines WHERE (which methods/classes) an Aspect should be applied.
It is an expression used inside an Advice annotation.
✅ Example: Apply to All Methods in PaymentService

@Pointcut("execution(* com.example.service.PaymentService.*(..))")
public void paymentServiceMethods() { }
This Pointcut matches all methods inside PaymentService.

@Before("paymentServiceMethods()")
public void logBefore() {
    System.out.println("Logging before payment method...");
}

5.  Target Object
The Target Object is the actual class on which AOP is applied.
Spring AOP uses proxy objects to apply aspects to target objects.
✅ Example:
If AOP is applied to PaymentService, then PaymentService is the target object.

