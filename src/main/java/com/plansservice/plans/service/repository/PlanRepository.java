package com.plansservice.plans.service.repository;

import com.plansservice.plans.service.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findByUserId(Long userId);
    List<Plan> findByUserIdAndStatus(Long userId, String status);

}