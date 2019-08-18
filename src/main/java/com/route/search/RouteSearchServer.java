package com.route.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.route.search" })
public class RouteSearchServer {

	public static void main(String... args) {
		SpringApplication.run(RouteSearchServer.class, args);
	}
}
