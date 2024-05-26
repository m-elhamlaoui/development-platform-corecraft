package com.statsservice.stats.service.model;

import lombok.Data;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "stats")
@Data
public class Stat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Integer workoutsCompleted;

    private Double currentWeight;

    private LocalDate recordDate;
}
