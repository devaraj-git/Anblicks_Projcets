package com.Microservice.Employee_Eurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EmployeeEurekaclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeEurekaclientApplication.class, args);
	}

}
