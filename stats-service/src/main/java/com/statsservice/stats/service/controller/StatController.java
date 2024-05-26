package com.statsservice.stats.service.controller;

import com.statsservice.stats.service.model.Stat;
import com.statsservice.stats.service.model.UserDTO;
import com.statsservice.stats.service.service.StatService;
import com.statsservice.stats.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stats")
public class StatController {

    @Autowired
    private StatService StatService;

    @Autowired
    private UserService userService;



    @PostMapping("/")
    public ResponseEntity<Stat> createOrUpdateStat(@RequestBody Stat Stat,
                                                   @RequestHeader("Authorization") String jwt) {

        UserDTO user = userService.getUserProfile(jwt);
        Stat updatedStat = StatService.logWorkoutCompletion(user.getId(), Stat.getCurrentWeight());
        return ResponseEntity.ok(updatedStat);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Stat>> getStatByUserId(@PathVariable Long userId,
                                                      @RequestHeader("Authorization") String jwt) {
        UserDTO user = userService.getUserProfile(jwt);
        List<Stat> Stat = StatService.getStatByUserId(user.getId());
        return ResponseEntity.ok(Stat);
    }




}

