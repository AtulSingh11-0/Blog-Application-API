package com.blog.blogapplication.exception;

/**
 * This exception is thrown when a requested resource is not found.
 */
public class ResourceNotFoundException extends RuntimeException {

  /** The name of the resource that was not found. */
  private String resourceName;

  /** The name of the field by which the resource was searched. */
  private String fieldName;

  /** The value of the field by which the resource was searched. */
  private long fieldValue;

  /**
   * Constructs a new ResourceNotFoundException with the specified resource name, field name, and field value.
   *
   * @param resourceName The name of the resource that was not found.
   * @param fieldName    The name of the field by which the resource was searched.
   * @param fieldValue   The value of the field by which the resource was searched.
   */
  public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
    super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
    this.resourceName = resourceName;
    this.fieldName = fieldName;
    this.fieldValue = fieldValue;
  }
}
