**Weather API Project**

A Spring Boot application that fetches weather data from an external API and caches frequently requested locations using Redis for improved performance.

**Project Overview**

This application provides a RESTful API that accepts location requests and returns weather data. It implements caching to reduce external API calls and improve response times for frequently requested locations.

**Technologies Used**

Java 17

Spring Boot 3.3.0

Maven - Dependency management

Redis - Caching layer

RestTemplate - HTTP client for external API calls

Jackson - JSON serialization/deserialization

Lombok - Reducing boilerplate code

Docker - Redis containerization
