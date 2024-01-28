package com.stockapp.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class StockappEurekaserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockappEurekaserverApplication.class, args);
	}

}
