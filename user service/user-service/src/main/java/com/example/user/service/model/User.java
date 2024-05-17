package com.example.user.service.model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
