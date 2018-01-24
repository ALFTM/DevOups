package com.esgi.devops.reactive.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class WebApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebApp.class, args);
    }
}
