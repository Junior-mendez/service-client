package com.co.pragma.serviceclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootConfiguration
@SpringBootApplication
@EnableFeignClients
public class ServiceClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceClienteApplication.class, args);
	}

}
