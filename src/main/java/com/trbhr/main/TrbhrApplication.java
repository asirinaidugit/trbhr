package com.trbhr.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.trbhr")
public class TrbhrApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrbhrApplication.class, args);
    }
}
