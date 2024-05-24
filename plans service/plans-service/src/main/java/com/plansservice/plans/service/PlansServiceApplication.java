package com.plansservice.plans.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class PlansServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlansServiceApplication.class, args);
	}

}
