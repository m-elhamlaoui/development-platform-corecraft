package com.plansservice.plans.service.model;
import lombok.Data;
import jakarta.persistence.*;
import java.util.List;


@Entity
@Table(name = "plans")
@Data
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;  // Detailed description of the plan
    private Long userId;  // Null if it's a predefined plan, user ID if custom
    private String status;  // Example values: "Pending", "Active", "Completed"

    @ElementCollection
    private List<Long> workoutIds;  // IDs of workouts included in the plan

}
