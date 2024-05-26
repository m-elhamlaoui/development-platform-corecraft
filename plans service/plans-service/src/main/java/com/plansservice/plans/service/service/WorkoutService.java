package com.plansservice.plans.service.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import com.plansservice.plans.service.model.WorkoutDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

@FeignClient(name = "workout-service", url = "http://localhost:8081")
public interface WorkoutService {

    @GetMapping("/api/workouts/")
    public List<WorkoutDTO> getAllWorkouts();

}
