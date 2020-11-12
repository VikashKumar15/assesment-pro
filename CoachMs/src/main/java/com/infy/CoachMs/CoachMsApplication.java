package com.infy.CoachMs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class CoachMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoachMsApplication.class, args);
	}

}
