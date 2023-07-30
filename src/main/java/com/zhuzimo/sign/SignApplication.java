package com.zhuzimo.sign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SignApplication {

    public static void main(String[] args) {
        SpringApplication.run(SignApplication.class, args);
        log.info("服务启动");
    }

}
