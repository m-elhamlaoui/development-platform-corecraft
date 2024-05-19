package com.workoutsservice.workouts.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WorkoutsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkoutsServiceApplication.class, args);
	}

}
