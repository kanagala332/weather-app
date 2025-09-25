package com.example.weather_app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.weather_app.entity.Alert;

public interface AlertRepository extends JpaRepository<Alert, Long>{
	List<Alert> findByCityIgnoreCase(String city);
}
