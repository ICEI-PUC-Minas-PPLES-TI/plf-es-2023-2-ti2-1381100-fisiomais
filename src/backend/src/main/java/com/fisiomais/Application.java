package com.fisiomais;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Initializes our RESTful API.
 */
@OpenAPIDefinition(info = @Info(title = "Rest API Fisiomais", version = "1.0"))
@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(basePackages = "com.fisiomais")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
