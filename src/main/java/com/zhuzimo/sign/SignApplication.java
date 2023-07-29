package com.zhuzimo.sign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

@SpringBootApplication
public class SignApplication {

    @Resource
    private JDComponent jdComponent;

    @Resource
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(SignApplication.class, args);
    }

}
