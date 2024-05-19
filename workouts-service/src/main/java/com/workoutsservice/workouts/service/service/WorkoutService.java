package com.workoutsservice.workouts.service.service;

import com.workoutsservice.workouts.service.model.Workout;
import com.workoutsservice.workouts.service.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private UserService UserService;

    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAll();
    }

    public Workout addWorkout(Workout workout, Long userId) {
        workout.setUserId(userId);
        return workoutRepository.save(workout);
    }


    public List<Workout> getWorkoutsByBodyPart(String bodyPart) {
        return workoutRepository.findByBodyPart(bodyPart);
    }

    public boolean deleteWorkout(Long id) {
        if (workoutRepository.existsById(id)) {
            workoutRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
