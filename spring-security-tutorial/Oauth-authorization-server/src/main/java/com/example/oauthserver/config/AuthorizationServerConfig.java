package com.example.oauthserver.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration(proxyBeanMethods = false)
public class AuthorizationServerConfig {
    private PasswordEncoder passwordEncoder;

}
