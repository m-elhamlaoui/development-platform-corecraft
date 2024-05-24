package com.example.user.service.controller;

import com.example.user.service.config.JwtProvider;
import com.example.user.service.repository.UserRepository;
import com.example.user.service.request.LoginRequest;
import com.example.user.service.response.AuthResponse;
import com.example.user.service.service.CustomerUserServiceImplementation;
import com.example.user.service.service.UserService;
import com.example.user.service.model.User;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private CustomerUserServiceImplementation customUserDetails;

    @Autowired
    private UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {
        // Extracting user information from the request body
        String email = user.getEmail();
        String password = user.getPassword();

        // Checking if the email is already used
        User existingUser = userRepository.findByEmail(email);

        if (existingUser != null) {
            throw new Exception("Email Is Already Used With Another Account");
        }

        // Encrypting the user password
        user.setPassword(passwordEncoder.encode(password));

        // Saving the user to the database
        User savedUser = userRepository.save(user);

        // Authenticating the user
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generating a JWT token for the authenticated user
        String token = JwtProvider.generateToken(authentication);

        // Creating the authentication response
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Register Success");
        authResponse.setStatus(true);

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) {

        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        System.out.println(username+"-------"+password);

        Authentication authentication = authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();

        authResponse.setMessage("Login success");
        authResponse.setJwt(token);
        authResponse.setStatus(true);

        return new ResponseEntity<>(authResponse,HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {



        UserDetails userDetails = customUserDetails.loadUserByUsername(username);

        System.out.println("Sign in in user details"+ userDetails);

        if(userDetails == null) {
            System.out.println("Sign in details - null" + userDetails);

            throw new BadCredentialsException("Invalid username and password");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())) {
            System.out.println("Sign in userDetails - password mismatch"+userDetails);

            throw new BadCredentialsException("Invalid password");

        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

    }

}
