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

/**
 * This class provides global exception handling for the blogging application.
 */
@RestControllerAdvice
public class GloabalExceptionHandler {

  /**
   * Handles ResourceNotFoundException and returns an appropriate response.
   *
   * @param e The ResourceNotFoundException instance.
   * @return ResponseEntity<ApiResponse> The response entity containing an appropriate API response.
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
    String message = e.getMessage();
    ApiResponse apiResponse = new ApiResponse(message, false);
    return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
  }

  /**
   * Handles MethodArgumentNotValidException and returns an appropriate response.
   *
   * @param ex The MethodArgumentNotValidException instance.
   * @return ResponseEntity<Map<String, String>> The response entity containing field errors.
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleMethodArgumentsNotValidException(MethodArgumentNotValidException ex) {
    Map<String, String> result = new HashMap<>();

    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String message = error.getDefaultMessage();
      result.put(fieldName, message);
    });
    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
  }
}