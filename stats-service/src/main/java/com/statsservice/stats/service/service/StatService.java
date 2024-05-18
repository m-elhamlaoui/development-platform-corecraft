package com.statsservice.stats.service.service;

import com.statsservice.stats.service.model.Stat;
import com.statsservice.stats.service.repository.StatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StatService {

    @Autowired
    private StatRepository StatRepository;

    public Stat logWorkoutCompletion(Long userId, Double currentWeight) {
        Stat Stat = new Stat();
        Stat.setUserId(userId);
        Stat.setWorkoutsCompleted(1); // Increment logic should be handled if previous records exist
        Stat.setCurrentWeight(currentWeight);
        Stat.setRecordDate(LocalDate.now());
        return StatRepository.save(Stat);
    }

    public List<Stat> getStatByUserId(Long userId) {
        return StatRepository.findByUserId(userId);
    }


}
