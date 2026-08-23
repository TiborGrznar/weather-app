package com.tgrznar.weather_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.tgrznar.weather_app.dto.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<ErrorResponse> handleCityNotFound(HttpClientErrorException.NotFound ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("City not found"));
    }
}