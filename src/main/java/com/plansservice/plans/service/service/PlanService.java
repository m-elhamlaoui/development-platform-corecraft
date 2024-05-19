package com.plansservice.plans.service.service;

import com.plansservice.plans.service.model.Plan;
import com.plansservice.plans.service.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    public Plan createOrUpdatePlan(Plan plan) {
        return planRepository.save(plan);
    }

    public Optional<Plan> getPlanById(Long planId) {
        return planRepository.findById(planId);
    }

    public List<Plan> getPlansByUserIdAndStatus(Long userId, String status) {
        return planRepository.findByUserIdAndStatus(userId, status);
    }

    public List<Plan> getPlansByUserId(Long userId) {
        return planRepository.findByUserId(userId);
    }

    public Plan updatePlanStatus(Long planId, String status) {
        Optional<Plan> planOptional = planRepository.findById(planId);
        if (planOptional.isPresent()) {
            Plan plan = planOptional.get();
            plan.setStatus(status);
            return planRepository.save(plan);
        } else {
            throw new IllegalStateException("Plan with ID " + planId + " not found.");
        }
    }
}

