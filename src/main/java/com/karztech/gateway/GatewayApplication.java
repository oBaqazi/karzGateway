package com.karztech.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {


	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()

				.route(p -> p
						.path("/api/chat") // intercept calls to the /get path
						.uri("http://167.99.246.161:8091"))
				.route(p -> p
						.path("/api/**") // intercept calls to the /get path
						.uri("https://dev.api.karztech.com"))
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
		System.out.println("Hello hi");
	}

}
