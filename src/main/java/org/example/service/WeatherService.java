package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.model.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class WeatherService {

    @Value("${weather.api.baseUrl}")
    private String baseUrl;

    @Value("${weather.api.accessKey}")
    private String accessKey;

    private final RestTemplate restTemplate;

    @Autowired
    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("weatherByLocation")
    public WeatherResponse fetchWeatherForLocation(String location) {
        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("location cannot be null");
        }
        log.info("Cache MISS - Fetching weather data from external API for location: {}", location);
        String url = baseUrl + location + accessKey;
        return restTemplate.getForObject(url, WeatherResponse.class);
    }
}
