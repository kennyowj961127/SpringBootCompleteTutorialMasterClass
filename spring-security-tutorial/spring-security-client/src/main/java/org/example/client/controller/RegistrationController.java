package org.example.client.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.client.entity.User;
import org.example.client.entity.VerificationToken;
import org.example.client.event.RegistrationCompleteEvent;
import org.example.client.model.PasswordModel;
import org.example.client.model.UserModel;
import org.example.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
public class RegistrationController {

    private UserService userService;

    private ApplicationEventPublisher applicationEventPublisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request) {
        User user = userService.registerUser(userModel);
        applicationEventPublisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "User registered successfully";
    }

    @GetMapping("/resendRegistrationToken")
    public String resendRegistrationToken(@RequestParam("token") String oldToken, final HttpServletRequest request) {
        VerificationToken verificationToken = userService.generateNewVerificationToken(oldToken);
        if (verificationToken == null) {
            return "Invalid token";
        }
        User user = verificationToken.getUser();
        resendVerificationTokenEmail(user, applicationUrl(request), verificationToken);
        return "Token resent successfully";
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token) {
        log.info("Token received: {}", token);
        String result = userService.validateVerificationToken(token);
        if (result.equals("valid")) {
            return "User verified successfully";
        } else if (result.equals("expired")) {
            return "Token expired";
        } else {
            return "Invalid token";
        }
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody PasswordModel passwordModel, final HttpServletRequest request) {
        User user = userService.findUserByEmail(passwordModel.getEmail());
        String url = "";
        if (user != null) {
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);
            url = passwordResetTokenEmail(user, applicationUrl(request), token);
        }
        return url;
    }

    @PostMapping("/savePassword")
    public String savePassword(@RequestBody PasswordModel passwordModel, @RequestParam("token") String token) {
        String result = userService.validatePasswordResetToken(token);
        if (result.equalsIgnoreCase("valid")) {
            Optional<User> user = userService.findUserByPasswordResetToken(token);
            if (user.isPresent()) {
                User resetUser = user.get();
                resetUser.setPassword(passwordModel.getPassword());
                userService.registerUser(resetUser);
                return "Password reset successfully";
            } else {
                return "Invalid token";
            }
        } else if (result.equals("expired")) {
            return "Token expired";
        } else {
            return "Invalid token";
        }
    }

    private String passwordResetTokenEmail(User user, String s, String token) {
        log.info("Sending password reset token email to user: {}", s + "/savePassword?token=" + token);
        return s + "/resetPassword?token=" + token;
    }

    private String applicationUrl(HttpServletRequest request) {
        log.info("Original UR: {}", request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath());
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    private void resendVerificationTokenEmail(User user, String s, VerificationToken verificationToken) {
        log.info("Resending verification token email to user: {}", s + "/verifyRegistration?token=" + verificationToken.getToken());

    }
}
