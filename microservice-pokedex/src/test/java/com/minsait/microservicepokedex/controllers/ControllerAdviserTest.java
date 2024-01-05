package com.minsait.microservicepokedex.controllers;

import com.minsait.controllers.ControllerAdviser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;


public class ControllerAdviserTest {

    @InjectMocks
    private ControllerAdviser controllerAdvice;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHandleNoSuchElementException() {
        NoSuchElementException exception = new NoSuchElementException("Resource not found");

        ResponseEntity<?> responseEntity = controllerAdvice.notFoundException(exception);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody()).isInstanceOf(Map.class);
        Map<String, Object> responseBody = (Map<String, Object>) responseEntity.getBody();
        assertThat(responseBody).containsEntry("status", "404");
        assertThat(responseBody).containsEntry("Error, ", "Resource not found");

    }
}


