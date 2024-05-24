package com.plansservice.plans.service.controller;

import com.plansservice.plans.service.service.PlanService;
import com.plansservice.plans.service.model.Plan;

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


    @PostMapping("/")
    public ResponseEntity<Plan> createPlan(@RequestBody Plan plan) {
        Plan savedPlan = planService.createOrUpdatePlan(plan);
        return ResponseEntity.ok(savedPlan);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plan> getPlanById(@PathVariable Long id) {
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
