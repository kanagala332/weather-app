package com.example.weather_app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.weather_app.entity.WeatherData;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long>{
	List<WeatherData> findByCityIgnoreCase(String city);
}
