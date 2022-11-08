package com.finclientproj.Finclient;

import Model.CovidData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class FinclientApplication {

	@Autowired
	FeignUserClinet feignUserClinet;

	@GetMapping("/getData")
	public List<CovidData> getData(){
		return feignUserClinet.getCovidData();
	}

	public static void main(String[] args) {
		SpringApplication.run(FinclientApplication.class, args);
	}

}
