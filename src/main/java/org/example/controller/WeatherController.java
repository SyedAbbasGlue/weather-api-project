package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.model.response.WeatherResponse;
import org.example.service.WeatherService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class WeatherController {

    private WeatherService weatherService;
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public WeatherResponse getWeatherData(@RequestParam(name = "location") String location){
        WeatherResponse response = weatherService.fetchWeatherForLocation(location);
        log.info("Successfully retrieved weather data");
        return response;
    }
}
