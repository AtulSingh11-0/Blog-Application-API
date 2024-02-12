package com.blog.blogapplication.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents a generic API response.
 * It contains a message describing the result of an operation and a flag indicating success or failure.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

  /** The message describing the result of an operation. */
  private String message;

  /** A flag indicating whether the operation was successful or not. */
  private boolean success;
}
