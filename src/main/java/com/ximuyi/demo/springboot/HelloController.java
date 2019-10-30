package com.ximuyi.demo.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HelloController {

    @GetMapping("")
    public String index() {
        return "index, I's Jim, welcome to Spring Boot!";
    }

	@GetMapping("hello")
	public String hello() {
		return "hello, I's Jim, welcome to Spring Boot!";
	}
}
