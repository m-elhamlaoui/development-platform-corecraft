package com.example.user.service.controller;

import java.util.List;
import com.example.user.service.model.User;
import com.example.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody User login) {
//        boolean isAuthenticated = userService.loginUser(login.getEmail(), login.getPassword());
//        if (isAuthenticated) {
//            return ResponseEntity.ok("User authenticated successfully");
//        }
//        return ResponseEntity.status(401).body("Invalid credentials");
//    }
//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody User user) {
//        User savedUser = userService.registerUser(user);
//        return ResponseEntity.ok("User registered successfully with ID: " + savedUser.getId());
//    }

//    @GetMapping("/{email}")
//    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
//        User user = userService.getUserByEmail(email);
//        if (user != null) {
//            return ResponseEntity.ok(user);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt) {
            User user = userService.getUser(jwt);
            return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader("Authorization") String jwt) {

        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


    @GetMapping("/home")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to the User Dashboard !");
    }
}
