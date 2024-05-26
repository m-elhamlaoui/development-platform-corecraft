package com.workoutsservice.workouts.service.model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "workouts")
@Data
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String bodyPart; // e.g., Arms, Legs, etc.
    private String difficulty; // e.g., Beginner, Intermediate, Advanced
    private String photoUrl;
    private Long userId;
}