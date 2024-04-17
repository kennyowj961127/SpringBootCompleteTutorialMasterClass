package org.example;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthenticationAspect {

    @Pointcut("within(org..*)")
    public void authenticatingPointCut(){
    }


    @Pointcut("within(org..*)")
    public void authorizationPointCut(){
    }

    @Before("authenticatingPointCut() || authorizationPointCut()")
    public void authenticate(){
        System.out.println("Authentication Aspect is called");
    }
}
