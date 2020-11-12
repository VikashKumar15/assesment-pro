package com.infy.BookMs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableDiscoveryClient
public class BookinMsApplication {

	public static void main(String[] args) {
		SpringApplication.run( BookinMsApplication.class, args);
	}

}
