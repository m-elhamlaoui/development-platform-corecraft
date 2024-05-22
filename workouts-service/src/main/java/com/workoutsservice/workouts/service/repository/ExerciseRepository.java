package com.workoutsservice.workouts.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workoutsservice.workouts.service.model.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise,Long>{

  Optional<Exercise> findByName(String name);
  List<Exercise> findByWithEquipment(boolean withEquipment);
  
}
