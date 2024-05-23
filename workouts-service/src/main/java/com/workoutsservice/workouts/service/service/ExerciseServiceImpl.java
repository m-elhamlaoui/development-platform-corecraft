package com.workoutsservice.workouts.service.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.workoutsservice.workouts.service.model.Exercise;
import com.workoutsservice.workouts.service.repository.ExerciseRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class ExerciseServiceImpl implements ExerciseService{

  private final ExerciseRepository repository;

  public ExerciseServiceImpl(final ExerciseRepository repository){
    this.repository = repository;
  }

  @Override
  public ExerciseRepository getRepository(){
    return repository;
  }

  @Override
  public List<Exercise> getAllExercises() {
    return repository.findAll();
  }

  @Override
  public Optional<Exercise> getExerciseById(long id) {
    return repository.findById(id);
  }

  @Override
  public Optional<Exercise> getExerciseByName(String name) {
    return repository.findByName(name);
  }

  @Override
  public List<Exercise> getExercisesByEquipment(boolean withEquipment) {
    return repository.findByWithEquipment(withEquipment);
  }

  @Override
  public List<Exercise> getExercisesByTargetMuscles(int target) {
    return repository.findAll().stream().filter(ex -> ((ex.getTargetMuscles() & target) != 0))
    .collect(Collectors.toList());
  }

  @Override
  public Exercise addExercise(Exercise exercise) {
    if(exercise.getId() == null){
      return repository.save(exercise);
    }
    throw new IllegalStateException("To add an exercise, its id must be null !");
  }

  @Override
  public Exercise updateExercise(long id, Exercise exercise) {
    if(repository.existsById(id)){
      exercise.setId(id);
      return repository.save(exercise);
    }
    throw new IllegalStateException("exercise with id " + id + " does not exist !");
  }

  @Override
  public void deleteExercise(long id) {
    if(repository.existsById(id)){
      repository.deleteById(id);
    }else{
      throw new IllegalStateException("exercise with id " + id + " does not exist !");
    }
  }
  
}
