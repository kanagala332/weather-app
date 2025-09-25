package com.example.weather_app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.weather_app.entity.Alert;
import com.example.weather_app.entity.User;
import com.example.weather_app.entity.WeatherData;
import com.example.weather_app.repo.AlertRepository;
import com.example.weather_app.repo.UserRepository;
import com.example.weather_app.repo.WeatherDataRepository;



@Service
public class WeatherDataService {
	private final WeatherDataRepository weatherRepo;
	private final RestTemplate restTemp;
	private final AlertRepository alertRepo;
	private final UserRepository userRepo;
	
	@Value("${weather.api.key}")
	private String apiKey;
	
	@Value("${weather.api.url}")
	private String apiUrl;
	
	public WeatherDataService(WeatherDataRepository weatherRepo, AlertRepository alertRepo, UserRepository userRepo) {
		this.weatherRepo=weatherRepo;
		this.restTemp=new RestTemplate();
		this.alertRepo=alertRepo;
		this.userRepo=userRepo;
	}
	public WeatherData fetchAndSaveWeatherData(String city) {
        String url=UriComponentsBuilder.fromHttpUrl(apiUrl+"/weather")
        		.queryParam("q", city)
        		.queryParam("appid", apiKey)
        		.queryParam("units", "metric")
        		.toUriString();
        Map<String, Object> response = restTemp.getForObject(url, Map.class);
        
        if(response !=null) {
        	Map<String, Object> main= (Map<String, Object>) response.get("main");
        	Map<String, Object> wind= (Map<String, Object>) response.get("wind");
        	
        	WeatherData weatherData=new WeatherData();
        	
        	weatherData.setCity(city);
        	weatherData.setTemperature(Double.parseDouble(main.get("temp").toString()));
        	weatherData.setHumidity(Integer.parseInt(main.get("humidity").toString()));
        	weatherData.setWindSpeed(Double.parseDouble(wind.get("speed").toString()));
        	weatherData.setTimestamp(LocalDateTime.now());
        	
        	WeatherData savedData= weatherRepo.save(weatherData);
        	
        	generateUserAlerts(savedData);
        	
        	return savedData;
        	
        }
        return null;
    }
	private void generateUserAlerts(WeatherData data) {
        List<User> users = userRepo.findByPreferredLocationIgnoreCase(data.getCity());

        for (User user : users) {
            if (data.getTemperature() > 40) {
                createAlert(user, data.getCity(), "Extreme heat alert! Stay hydrated.", "HIGH");
            } else if (data.getTemperature() < 0) {
                createAlert(user, data.getCity(), "Freezing conditions. Dress warmly.", "MEDIUM");
            }

            if (data.getWindSpeed() > 50) {
                createAlert(user, data.getCity(), "Severe wind alert. Stay indoors.", "HIGH");
            }

            if (data.getHumidity() > 90) {
                createAlert(user, data.getCity(), "High humidity. Possible rainfall expected.", "LOW");
            }
        }
    }
	private void createAlert(User user,String city, String message, String severity) {
		Alert alert=new Alert();
		
		alert.setUser(user);
		alert.setCity(city);
		alert.setMesage(message);
		alert.setSeverity(severity);
		alert.setIssuedAt(LocalDateTime.now());
		
		alertRepo.save(alert);
		
	}
	public List<WeatherData> getAllWeatherData() {
        return weatherRepo.findAll();
    }
	public List<WeatherData> getWeatherByCity(String city) {
	        return weatherRepo.findByCityIgnoreCase(city);
	}
}
