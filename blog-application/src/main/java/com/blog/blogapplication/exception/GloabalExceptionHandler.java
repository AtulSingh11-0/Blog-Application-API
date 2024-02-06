package com.blog.blogapplication.exception;

import com.blog.blogapplication.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GloabalExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
    String message = e.getMessage();
    ApiResponse apiResponse = new ApiResponse(message, false);
    return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleMethodArgumentsNotValidException(MethodArgumentNotValidException ex) {
    Map<String, String> result = new HashMap<>();

    ex.getBindingResult().getAllErrors().forEach((error)->{
      String fieldName = ((FieldError)error).getField();
      String message = error.getDefaultMessage();
      result.put(fieldName, message);
    });
    return new ResponseEntity<Map<String, String>>(result, HttpStatus.BAD_REQUEST);
  }
}