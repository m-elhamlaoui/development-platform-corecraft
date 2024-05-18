package com.statsservice.stats.service.controller;

import com.statsservice.stats.service.model.Stat;
import com.statsservice.stats.service.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stats")
public class StatController {

    @Autowired
    private StatService StatService;



    @PostMapping("/")
    public ResponseEntity<Stat> createOrUpdateStat(@RequestBody Stat Stat) {
        Stat updatedStat = StatService.logWorkoutCompletion(Stat.getUserId(), Stat.getCurrentWeight());
        return ResponseEntity.ok(updatedStat);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Stat>> getStatByUserId(@PathVariable Long userId) {
        List<Stat> Stat = StatService.getStatByUserId(userId);
        return ResponseEntity.ok(Stat);
    }
}

