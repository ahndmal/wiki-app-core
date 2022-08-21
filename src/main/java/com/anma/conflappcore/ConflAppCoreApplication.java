package com.anma.conflappcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ConflAppCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConflAppCoreApplication.class, args);
    }

}
