package com.workoutsservice.workouts.service.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.workoutsservice.workouts.service.model.Exercise;
import com.workoutsservice.workouts.service.repository.ExerciseRepository;

@ExtendWith(MockitoExtension.class)
public class ExerciseRepositoryTest {

  @Mock
  private ExerciseRepository exerciseRepositoryMock;

  @Test
  public void testFindByName() {
    String exerciseName = "Push-up";
    Exercise exercise = Exercise.builder().id(1L).name(exerciseName).build();

    when(exerciseRepositoryMock.findByName(exerciseName)).thenReturn(Optional.of(exercise));

    Optional<Exercise> foundExercise = exerciseRepositoryMock.findByName(exerciseName);
    assertEquals(exerciseName, foundExercise.get().getName());
  }

  @Test
  public void testFindByWithEquipment() {
    boolean withEquipment = true;
    Exercise exercise1 = Exercise.builder().id(1L).withEquipment(true).build();
    Exercise exercise2 = Exercise.builder().id(2L).withEquipment(true).build();

    when(exerciseRepositoryMock.findByWithEquipment(withEquipment)).thenReturn(Arrays.asList(exercise1, exercise2));

    List<Exercise> foundExercises = exerciseRepositoryMock.findByWithEquipment(withEquipment);
    assertEquals(2, foundExercises.size());
    assertEquals(1L, foundExercises.get(0).getId());
    assertEquals(2L, foundExercises.get(1).getId());
  }
}
