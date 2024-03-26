package com.example.springsecurity.controller;

import com.example.springsecurity.entity.Auth;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.service.JwtService;
import com.example.springsecurity.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Doctor Appointment System!!!";
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> userRegistration(@RequestBody User user) {
        User user1 = userService.addUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public String userLogin(@RequestBody Auth auth) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getUserName(), auth.getPassword()));
        if (authenticate.isAuthenticated()) {
            return jwtService.generateToken(auth.getUserName());
        } else {
            throw new UsernameNotFoundException("Invalid user request");
        }
    }

    @GetMapping("/getUsers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping("/getUsers/{id}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public User getAllUsers(@PathVariable int id) {
        return userService.getUser(id);
    }

}
