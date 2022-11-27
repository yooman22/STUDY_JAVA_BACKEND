package com.example.API_PROJECT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//extends SpringBootServletInitializer
//exclude = {SecurityAutoConfiguration.class }
//
@SpringBootApplication()
public class ApiProject1Application extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ApiProject1Application.class, args);
	}
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ApiProject1Application.class);
    }

}
