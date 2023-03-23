package com.oop.leap_ahead_x;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class LeapAheadXApplication {

    public static void main(final String[] args) {
        SpringApplication.run(LeapAheadXApplication.class, args);
    }

}
