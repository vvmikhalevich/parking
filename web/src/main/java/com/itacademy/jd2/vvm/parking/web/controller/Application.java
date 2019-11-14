package com.itacademy.jd2.vvm.parking.web.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration

public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }
}
