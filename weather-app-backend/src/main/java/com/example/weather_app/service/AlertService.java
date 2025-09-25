package com.example.weather_app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.weather_app.entity.Alert;
import com.example.weather_app.repo.AlertRepository;

@Service
public class AlertService {
	private final AlertRepository alertRepository;
	
	public AlertService(AlertRepository alertRepository) {
		this.alertRepository=alertRepository;
	}
	
	public Alert saveAlert(Alert alert) {
        alert.setIssuedAt(LocalDateTime.now());
        return alertRepository.save(alert);
    }
	
	public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }
	
	public List<Alert> getAlertsByCity(String city) {
        return alertRepository.findByCityIgnoreCase(city);
    }
}
