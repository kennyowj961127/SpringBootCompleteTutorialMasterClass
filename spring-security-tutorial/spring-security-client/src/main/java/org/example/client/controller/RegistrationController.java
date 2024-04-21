package org.example.client.controller;

import lombok.AllArgsConstructor;
import org.example.client.entity.User;
import org.example.client.model.UserModel;
import org.example.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RegistrationController {

    private UserService userService;

    private ApplicationEventPublisher applicationEventPublisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel){
        User user = userService.registerUser(userModel);
        applicationEventPublisher.publishEvent(user);
        return "User registered successfully";
    }
}
