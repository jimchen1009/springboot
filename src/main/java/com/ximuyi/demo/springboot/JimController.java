package com.ximuyi.demo.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties({JimBean.class})
public class JimController {

    @Autowired
    private JimBean jimBean;

    @GetMapping("/jim")
    public String user() {
        return jimBean.toString();
    }
}
