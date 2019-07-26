package com.learning.spring_boot_mybatis_plus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.learning.**.dao")
public class Spring_boot_mybatis_plus_Application {

    public static void main(String[] args) {
        SpringApplication.run(Spring_boot_mybatis_plus_Application.class, args);
    }
}
