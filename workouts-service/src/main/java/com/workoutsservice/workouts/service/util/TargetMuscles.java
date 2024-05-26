package com.workoutsservice.workouts.service.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TargetMuscles {
  public static final int CHEST = 2, BACK = 1, ARM = 4, LEG = 8, SHOULDER = 16, ABS = 32;
  public static final List<String> targets = Collections.unmodifiableList(
      Arrays.asList("Back", "Chest", "Arm", "Leg", "Shoulder", "Abs"));

  public static List<String> getTarget(int target) {
    List<String> targetMuscles = new ArrayList<>();
    for (int i = 0; i < targets.size(); ++i) {
      if (((1 << i) & target) != 0) {
        targetMuscles.add(targets.get(i));
      }
    }
    return targetMuscles;
  }
}
