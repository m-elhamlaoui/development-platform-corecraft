package com.workoutsservice.workouts.service.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.workoutsservice.workouts.service.model.Exercise;
import com.workoutsservice.workouts.service.repository.ExerciseRepository;
import com.workoutsservice.workouts.service.service.ExerciseServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ExerciseServiceImplTest {

  @Mock
  private ExerciseRepository exerciseRepository;

  @InjectMocks
  private ExerciseServiceImpl exerciseService;

  @Test
  public void testRepositoryMockInjected() {
    assertNotNull(exerciseService.getRepository());
    assertEquals(exerciseRepository, exerciseService.getRepository());
  }

  @Test
  public void testGetAllExercises() {
    List<Exercise> exercises = Arrays.asList(new Exercise(), new Exercise());
    when(exerciseRepository.findAll()).thenReturn(exercises);

    List<Exercise> result = exerciseService.getAllExercises();
    assertEquals(2, result.size());
    verify(exerciseRepository, times(1)).findAll();
  }

  @Test
  public void testGetExerciseById() {
    Exercise exercise = new Exercise();
    exercise.setId(1L);
    when(exerciseRepository.findById(1L)).thenReturn(Optional.of(exercise));

    Optional<Exercise> result = exerciseService.getExerciseById(1L);
    assertEquals(true, result.isPresent());
    assertEquals(1L, result.get().getId());
    verify(exerciseRepository, times(1)).findById(1L);
  }

  @Test
  public void testGetExerciseByName() {
    Exercise exercise = new Exercise();
    exercise.setName("Push-up");
    when(exerciseRepository.findByName("Push-up")).thenReturn(Optional.of(exercise));

    Optional<Exercise> result = exerciseService.getExerciseByName("Push-up");
    assertEquals(true, result.isPresent());
    assertEquals("Push-up", result.get().getName());
    verify(exerciseRepository, times(1)).findByName("Push-up");
  }

  @Test
  public void testGetExercisesByEquipment() {
    Exercise exercise1 = new Exercise();
    exercise1.setWithEquipment(true);
    Exercise exercise2 = new Exercise();
    exercise2.setWithEquipment(true);
    List<Exercise> exercises = Arrays.asList(exercise1, exercise2);
    when(exerciseRepository.findByWithEquipment(true)).thenReturn(exercises);

    List<Exercise> result = exerciseService.getExercisesByEquipment(true);
    assertEquals(2, result.size());
    verify(exerciseRepository, times(1)).findByWithEquipment(true);
  }

  @Test
  public void testGetExercisesByTargetMuscles() {
    Exercise exercise1 = new Exercise();
    exercise1.setTargetMuscles(2); // CHEST
    Exercise exercise2 = new Exercise();
    exercise2.setTargetMuscles(4); // ARM
    List<Exercise> exercises = Arrays.asList(exercise1, exercise2);
    when(exerciseRepository.findAll()).thenReturn(exercises);

    List<Exercise> result = exerciseService.getExercisesByTargetMuscles(2);
    assertEquals(1, result.size());
    assertEquals(2, result.get(0).getTargetMuscles());
    verify(exerciseRepository, times(1)).findAll();
  }

  @Test
  public void testAddExercise() {
    Exercise exercise = new Exercise();
    exercise.setName("Push-up");
    when(exerciseRepository.save(exercise)).thenReturn(exercise);

    Exercise result = exerciseService.addExercise(exercise);
    assertEquals("Push-up", result.getName());
    verify(exerciseRepository, times(1)).save(exercise);
  }

  @Test
  public void testAddExerciseWithId() {
    Exercise exercise = new Exercise();
    exercise.setId(1L); // This should not be set manually
    exercise.setName("Push-up");

    Exception exception = assertThrows(IllegalStateException.class, () -> {
      exerciseService.addExercise(exercise);
    });

    String expectedMessage = "To add an exercise, its id must be null !";
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  @Test
  public void testUpdateExercise() {
    Exercise existingExercise = new Exercise();
    existingExercise.setId(1L);
    existingExercise.setName("Push-up");
    Exercise updatedExercise = new Exercise();
    updatedExercise.setName("Updated Push-up");

    when(exerciseRepository.existsById(1L)).thenReturn(true);
    when(exerciseRepository.save(any(Exercise.class))).thenReturn(updatedExercise);

    Exercise result = exerciseService.updateExercise(1L, updatedExercise);
    assertEquals("Updated Push-up", result.getName());
    verify(exerciseRepository, times(1)).existsById(1L);
    verify(exerciseRepository, times(1)).save(updatedExercise);
  }

  @Test
  public void testUpdateExerciseNotFound() {
    Exercise updatedExercise = new Exercise();
    updatedExercise.setName("Updated Push-up");
    when(exerciseRepository.existsById(1L)).thenReturn(false);

    Exception exception = assertThrows(IllegalStateException.class, () -> {
      exerciseService.updateExercise(1L, updatedExercise);
    });

    String expectedMessage = "exercise with id 1 does not exist !";
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  @Test
  public void testDeleteExercise() {
    long exerciseId = 1L;
    when(exerciseRepository.existsById(exerciseId)).thenReturn(true);

    exerciseService.deleteExercise(exerciseId);

    verify(exerciseRepository, times(1)).existsById(exerciseId);
    verify(exerciseRepository, times(1)).deleteById(exerciseId);
  }

  @Test
  public void testDeleteExerciseNotFound() {
    long exerciseId = 1L;
    when(exerciseRepository.existsById(exerciseId)).thenReturn(false);

    Exception exception = assertThrows(IllegalStateException.class, () -> {
      exerciseService.deleteExercise(exerciseId);
    });

    String expectedMessage = "exercise with id " + exerciseId + " does not exist !";
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);

    verify(exerciseRepository, times(1)).existsById(exerciseId);
    verify(exerciseRepository, never()).deleteById(exerciseId);
  }

}
