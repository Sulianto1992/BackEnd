package com.optimum.employeedashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
// Enables Swagger2 API documentation for whole project
public class EmployeedashboardApplication {

	// main
	public static void main(String[] args) {
		SpringApplication.run(EmployeedashboardApplication.class, args);
	}
	
	// @Bean
	// Optional annotation
	// if used, will return mappings for all controllers only
	// if did not use, will return all controllers + built in
	// error controller mappings, web-mvc-links-handler mappings
	// operation handler mappings
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.optimum.employeedashboard")).build();
	}
	// to access API list in swagger
	// http://localhost:8090/swagger-ui.html#
}
