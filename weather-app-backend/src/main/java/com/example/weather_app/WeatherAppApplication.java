package com.example.weather_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class WeatherAppApplication extends SpringBootServletInitializer {

    // Needed for WAR deployment to external Tomcat
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WeatherAppApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WeatherAppApplication.class, args);
        System.out.print("Hello world");
    }
}

