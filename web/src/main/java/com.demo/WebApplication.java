package com.demo;

import com.demo.router.LocationRouter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

// remove @SpringBootApplication and @ComponentScan, replace with @EnableAutoConfiguration
@SpringBootApplication
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

	@Bean
	public RouterFunction<ServerResponse> monoRouterFunction(LocationRouter locationRouter) {
		return locationRouter.routes();
	}
}
