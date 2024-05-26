package com.plansservice.plans.service.service;

import com.plansservice.plans.service.model.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service", url = "http://localhost:8080")
public interface UserService {

    @GetMapping("/api/users/user")
    public UserDTO getUserProfile(@RequestHeader("Authorization") String jwt);

}