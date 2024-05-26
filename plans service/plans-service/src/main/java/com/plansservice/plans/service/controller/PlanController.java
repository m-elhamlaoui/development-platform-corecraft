package com.plansservice.plans.service.controller;

import com.plansservice.plans.service.model.UserDTO;
import com.plansservice.plans.service.service.PlanService;
import com.plansservice.plans.service.model.Plan;

import com.plansservice.plans.service.service.UserService;
import com.plansservice.plans.service.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;




@RestController
@RequestMapping("/api/plans")
public class PlanController {
    @Autowired
    private PlanService planService;

    @Autowired
    private UserService userService;

    @Autowired
    private WorkoutService workoutService;


    @PostMapping("/")
    public ResponseEntity<Plan> createPlan(@RequestBody Plan plan,
                                           @RequestHeader("Authorization") String jwt) {
        UserDTO user = userService.getUserProfile(jwt);
        PlanService.createOrUpdatePlan(plan, user.getId());
        return ResponseEntity.ok(plan);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plan> getPlanById(@PathVariable Long id,
                                            @RequestHeader("Authorization") String jwt) {
        UserDTO user = userService.getUserProfile(jwt);
        Optional<Plan> plan = planService.getPlanById(id);
        return plan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}/status")
    public ResponseEntity<Plan> updatePlanStatus(@PathVariable Long id, @RequestParam String status) {
        Plan updatedPlan = planService.updatePlanStatus(id, status);
        return ResponseEntity.ok(updatedPlan);
    }

    @GetMapping("/user/{userId}/status")
    public ResponseEntity<List<Plan>> getPlansByStatus(@PathVariable Long userId, @RequestParam String status) {
        List<Plan> plans = planService.getPlansByUserIdAndStatus(userId, status);
        return ResponseEntity.ok(plans);
    }
}
