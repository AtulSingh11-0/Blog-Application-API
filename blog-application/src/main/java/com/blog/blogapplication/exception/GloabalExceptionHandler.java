package com.blog.blogapplication.exception;

import com.blog.blogapplication.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GloabalExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
    String message = e.getMessage();
    ApiResponse apiResponse = new ApiResponse(message, false);
    return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
  }
}
