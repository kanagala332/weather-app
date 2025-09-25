package com.example.weather_app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.weather_app.entity.Alert;
import com.example.weather_app.repo.AlertRepository;
import com.example.weather_app.repo.UserRepository;


@RestController
@RequestMapping("/api/alerts")
public class AlertController {
	private final AlertRepository alertRepo;
	private final UserRepository userRepo;
	
	public AlertController(AlertRepository alertRepo,UserRepository userRepo) {
		this.alertRepo=alertRepo;
		this.userRepo=userRepo;
	}
	
	@GetMapping
	public List<Alert> getAllAlerts() {
        return alertRepo.findAll();
    }
	
	@GetMapping("/{userId}")
    public List<Alert> getAlertForUser(@PathVariable Long userId) {
		return userRepo.findById(userId)
				.map(user->user.getAlerts())
				.orElseThrow(()-> new RuntimeException("User not found"));
    }
	
	@GetMapping("/{city}")
	public List<Alert> getAlertByCity(@PathVariable String city){
		return alertRepo.findByCityIgnoreCase(city);
	}
	
	@DeleteMapping("/{id}")
	public void deleteAlert(@PathVariable Long id) {
        alertRepo.deleteById(id);
    }
}
