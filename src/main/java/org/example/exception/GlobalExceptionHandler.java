package org.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.customHandlers.ResourceNotFoundException;
import org.example.model.response.error.ErrorResponse;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e){
        ErrorResponse errorResponse = new ErrorResponse(
                404,
                e.getMessage(),
                "The resource was not found.",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(IllegalArgumentException e){
        ErrorResponse errorResponse = new ErrorResponse(
                400,
                "INVALID_INPUT",
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ErrorResponse> handleApiConnectionError(RestClientException e){
        log.error("External API connection failed: {}", e.getMessage());
        ErrorResponse error = new ErrorResponse(
                500,
                "EXTERNAL_API_ERROR",
                "Unable to fetch weather data. Please try again later.",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(RedisConnectionFailureException.class)
    public ResponseEntity<ErrorResponse> handleCacheException(RedisConnectionFailureException e){
        log.error("Redis connection failed: {}", e.getMessage());
        ErrorResponse error = new ErrorResponse(
                500,
                "CACHE_ERROR",
                "Service caching unavailable",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
