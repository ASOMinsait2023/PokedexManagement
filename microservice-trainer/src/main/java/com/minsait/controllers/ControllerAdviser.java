package com.minsait.controllers;

import feign.FeignException;
import org.springframework.data.repository.query.ParameterOutOfBoundsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerAdviser {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> notFoundException(NoSuchElementException exception){
        Map<String,Object> response = new HashMap<>();
        response.put("status","404");
        response.put("Error, ", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ParameterOutOfBoundsException.class)
    public ResponseEntity<?> parameterOutOfBound(ParameterOutOfBoundsException exception){
        Map<String,Object> response = new HashMap<>();
        response.put("status","400");
        response.put("Error, ", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<?> sqlExceptionHandler(SQLException exception){
        Map<String, Object> response = new HashMap<>();
        response.put("status", exception.getErrorCode());
        response.put("Error, ", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(FeignException.UnsupportedMediaType.class)
    public ResponseEntity<?> UnsupportedMediaType(FeignException.UnsupportedMediaType ex){

        Map<String, Object> response = new HashMap<>();
        response.put("status", ex.status());
        response.put("Error, ", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(FeignException.InternalServerError.class)
    public ResponseEntity<?> handleFeignInternalServerError(FeignException.InternalServerError ex) {

        Map<String, Object> response = new HashMap<>();

        response.put("status", ex.status());
        response.put("Error, ", ex.getMessage());
        return  new ResponseEntity<>(response, HttpStatusCode.valueOf(ex.status()));
    }
    


}
