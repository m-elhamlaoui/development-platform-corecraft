package com.workoutsservice.workouts.service.service;

import java.util.List;
import java.util.Optional;

import com.workoutsservice.workouts.service.model.Exercise;
import com.workoutsservice.workouts.service.repository.ExerciseRepository;

public interface ExerciseService {

  public ExerciseRepository getRepository();
  public List<Exercise> getAllExercises();
  public Optional<Exercise> getExerciseById(long id);
  public Optional<Exercise> getExerciseByName(String name);
  public List<Exercise> getExercisesByEquipment(boolean withEquipment);
  public List<Exercise> getExercisesByTargetMuscles(int target);
  public Exercise addExercise(Exercise exercise);
  public Exercise updateExercise(long id, Exercise exercise);
  public void deleteExercise(long id);

}
