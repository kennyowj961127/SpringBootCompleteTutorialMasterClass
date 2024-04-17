package org.example.springboottutorial.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController()
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello, World!!!";
    }

}
