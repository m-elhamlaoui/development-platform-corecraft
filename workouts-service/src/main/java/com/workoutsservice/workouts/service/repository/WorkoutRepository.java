package com.workoutsservice.workouts.service.repository;

import com.workoutsservice.workouts.service.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByBodyPart(String bodyPart);
    List<Workout> findByUserId(Long userId);
}