package com.workoutsservice.workouts.service.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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

@ExtendWith(MockitoExtension.class)
public class ExerciseRepositoryTest {

  @Mock
  private ExerciseRepository exerciseRepositoryMock;

  @InjectMocks
  private ExerciseRepositoryTest exerciseRepositoryTest;

  @Test
  public void testFindByName() {
    // Mock data
    String exerciseName = "Push-up";
    Exercise exercise = new Exercise();
    exercise.setId(1L);
    exercise.setName(exerciseName);

    // Mock repository behavior
    when(exerciseRepositoryMock.findByName(exerciseName)).thenReturn(Optional.of(exercise));

    // Test
    Optional<Exercise> foundExercise = exerciseRepositoryTest.findExerciseByName(exerciseName);
    assertEquals(exerciseName, foundExercise.get().getName());
  }

  @Test
  public void testFindByWithEquipment() {
    // Mock data
    boolean withEquipment = true;
    Exercise exercise1 = new Exercise();
    exercise1.setId(1L);
    exercise1.setWithEquipment(true);

    Exercise exercise2 = new Exercise();
    exercise2.setId(2L);
    exercise2.setWithEquipment(true);

    // Mock repository behavior
    when(exerciseRepositoryMock.findByWithEquipment(withEquipment)).thenReturn(Arrays.asList(exercise1, exercise2));

    // Test
    List<Exercise> foundExercises = exerciseRepositoryTest.findExercisesWithEquipment(withEquipment);
    assertEquals(2, foundExercises.size());
    assertEquals(1L, foundExercises.get(0).getId());
    assertEquals(2L, foundExercises.get(1).getId());
  }

  private Optional<Exercise> findExerciseByName(String name) {
    return exerciseRepositoryMock.findByName(name);
  }

  private List<Exercise> findExercisesWithEquipment(boolean withEquipment) {
    return exerciseRepositoryMock.findByWithEquipment(withEquipment);
  }
}
