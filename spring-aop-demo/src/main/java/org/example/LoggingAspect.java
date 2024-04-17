package org.example;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* org.example.ShoppingCart.checkout())")
    public void logger(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature());

        System.out.println("Logging Aspect called");
    }

    @After("execution(* org.example.ShoppingCart.checkout())")
    public void loggerAfter(){
        System.out.println("Logging Aspect called after the method");
    }

    @AfterThrowing("execution(* org.example.ShoppingCart.checkout())")
    public void loggerAfterThrowing(){
        System.out.println("Logging Aspect called after the method throws an exception");
    }

    @Pointcut("execution(* org.example.ShoppingCart.quantity())")
    public void quantityPointCut(){}

    @AfterReturning(pointcut = "quantityPointCut()", returning = "retVal")
    public void loggerAfterReturning(String retVal){
        System.out.println("Logging Aspect called after the method returns a value" + retVal);
    }
}
