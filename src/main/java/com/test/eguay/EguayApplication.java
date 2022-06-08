package com.test.eguay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EguayApplication {

    public static void main(String[] args) {
        SpringApplication.run(EguayApplication.class, args);
    }

}
