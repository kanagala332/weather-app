package com.example.weather_app.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.weather_app.entity.User;
import com.example.weather_app.repo.UserRepository;
import com.example.weather_app.service.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private final UserRepository userRepo;
	
	@Autowired
	private UserService userService;
	
	public UserController(UserRepository userRepo) {
		this.userRepo=userRepo;
	}
	
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody User user) {
	    if (userRepo.findAll().stream().anyMatch(u -> u.getEmail().equals(user.getEmail()))) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists!");
	    }
	    return ResponseEntity.ok(userRepo.save(user));
	}


	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User loginRequest) {
	    return userRepo.findByEmailIgnoreCase(loginRequest.getEmail())
	            .filter(u -> u.getPassword().equals(loginRequest.getPassword()))
	            .<ResponseEntity<?>>map(ResponseEntity::ok)
	            .orElseGet(() -> ResponseEntity
	                .status(HttpStatus.UNAUTHORIZED)
	                .body(Map.of("error", "Invalid credentials")));
	}
	
	
	@GetMapping("/message")
	public String firstMessage() {
		return "Hello World";
	}
	
	@GetMapping
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
	
	@GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userRepo.findById(id);
    }
	
	@PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userRepo.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setPreferredLocation(updatedUser.getPreferredLocation());
            return userRepo.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }
	
	@DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepo.deleteById(id);
    }

}
