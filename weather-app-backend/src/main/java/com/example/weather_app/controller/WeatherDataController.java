package com.example.weather_app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.weather_app.entity.WeatherData;
import com.example.weather_app.service.WeatherDataService;

@RestController
@RequestMapping("/api/weather")
public class WeatherDataController {
	
	private final WeatherDataService weatherService;
	
	public WeatherDataController(WeatherDataService weatherService) {
		this.weatherService=weatherService;
	}
	
	@GetMapping("/live/{city}")
    public WeatherData getLiveWeather(@PathVariable String city) {
        return weatherService.fetchAndSaveWeatherData(city);
    }
	
	@GetMapping
    public List<WeatherData> getAllWeatherData() {
        return weatherService.getAllWeatherData();
    }
	
	@GetMapping("/{city}")
    public List<WeatherData> getWeatherByCity(@PathVariable String city) {
        return weatherService.getWeatherByCity(city);
    }
}
