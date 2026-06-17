package com.retail.returns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Order Returns System.
 * A simple Spring Boot service that manages return requests for an e-commerce/retail platform.
 */
@SpringBootApplication
public class ReturnsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReturnsApplication.class, args);
    }
}
