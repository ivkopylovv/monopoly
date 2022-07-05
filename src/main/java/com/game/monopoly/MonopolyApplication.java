package com.game.monopoly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MonopolyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonopolyApplication.class, args);
    }

}
