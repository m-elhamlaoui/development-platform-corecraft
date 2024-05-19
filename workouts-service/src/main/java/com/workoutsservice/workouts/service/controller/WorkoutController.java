package com.workoutsservice.workouts.service.controller;

import com.workoutsservice.workouts.service.model.Workout;
import com.workoutsservice.workouts.service.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @GetMapping("/")
    public ResponseEntity<List<Workout>> getAllWorkouts() {
        return ResponseEntity.ok(workoutService.getAllWorkouts());
    }

    @PostMapping("/addworkout/{userId}")
    public ResponseEntity<Workout> addWorkout(@RequestBody Workout workout,@PathVariable Long userId) {
        Workout savedWorkout = workoutService.addWorkout(workout, userId);
        return ResponseEntity.ok(savedWorkout);
    }

    @GetMapping("/bodyPart/{bodyPart}")
    public ResponseEntity<List<Workout>> getWorkoutsByBodyPart(@PathVariable String bodyPart) {
        List<Workout> workouts = workoutService.getWorkoutsByBodyPart(bodyPart);
        return ResponseEntity.ok(workouts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorkout(@PathVariable Long id) {
        if (workoutService.deleteWorkout(id)) {
            return ResponseEntity.ok("Workout deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}