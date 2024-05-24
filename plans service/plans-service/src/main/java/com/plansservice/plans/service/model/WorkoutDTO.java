package com.plansservice.plans.service.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutDTO {
    private Long id;
    private String name;
    private String description;
    private String bodyPart; // e.g., Arms, Legs, etc.
    private String difficulty; // e.g., Beginner, Intermediate, Advanced
    private String photoUrl;
    private Long userId;
}
