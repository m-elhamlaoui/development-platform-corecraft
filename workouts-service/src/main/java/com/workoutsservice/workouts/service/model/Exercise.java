package com.workoutsservice.workouts.service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Exercise")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true,nullable = false)
  private String name;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private boolean withEquipment;

  @Column(nullable = false)
  private int targetMuscles;
  
  @Lob
  private byte[] image;

  @Lob
  private byte[] video;

  @Column(nullable = false)
  private String instructions;
}
