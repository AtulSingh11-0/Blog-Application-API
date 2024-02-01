package com.blog.blogapplication.exception;

public class ResourceNotFoundException extends RuntimeException {
  String resourceName;
  String fieldName;
  long fieldValue;

  public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
    super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
    this.resourceName = resourceName;
    this.fieldName = fieldName;
    this.fieldValue = fieldValue;
  }
}

/*
* Whenever a Resource is not found for the corresponding id we throw this customException
* */