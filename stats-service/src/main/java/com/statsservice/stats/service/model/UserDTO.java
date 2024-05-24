package com.statsservice.stats.service.model;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;

    private String name;
    private String email;
    private String password;
    private String gender;
    private int age;
    private double height; // in centimeters
    private double weight; // in kilograms
    private String workoutDifficulty;
    private String workoutPreference;
    private double weightGoal; // in kilograms
}
