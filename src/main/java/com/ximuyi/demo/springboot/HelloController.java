package com.ximuyi.demo.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HelloController {

    @GetMapping("")
    public String index() {
        return "I's Jim, welcome to Spring Boot!";
    }
}
