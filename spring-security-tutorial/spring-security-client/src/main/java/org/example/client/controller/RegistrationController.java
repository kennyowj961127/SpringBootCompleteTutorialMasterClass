package org.example.client.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.client.entity.User;
import org.example.client.entity.VerificationToken;
import org.example.client.event.RegistrationCompleteEvent;
import org.example.client.model.UserModel;
import org.example.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
public class RegistrationController {

    private UserService userService;

    private ApplicationEventPublisher applicationEventPublisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request){
        User user = userService.registerUser(userModel);
        applicationEventPublisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "User registered successfully";
    }

    @GetMapping("/resendRegistrationToken")
    public String resendRegistrationToken(@RequestParam("token") String oldToken, final HttpServletRequest request){
        VerificationToken verificationToken = userService.generateNewVerificationToken(oldToken);
        if(verificationToken == null){
            return "Invalid token";
        }
        User user = verificationToken.getUser();
        resendVerificationTokenEmail(user, applicationUrl(request), verificationToken);
        return "Token resent successfully";
    }

    private void resendVerificationTokenEmail(User user, String s, VerificationToken verificationToken) {
        log.info("Resending verification token email to user: {}", s+ "/verifyRegistration?token="+verificationToken.getToken());

    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token){
        log.info("Token received: {}", token);
        String result = userService.validateVerificationToken(token);
        if(result.equals("valid")){
            return "User verified successfully";
    }else if(result.equals("expired")){
            return "Token expired";
        }else{
            return "Invalid token";
        }
    }

    private String applicationUrl(HttpServletRequest request) {
//        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        log.info("Original UR: {}", request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath());
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
