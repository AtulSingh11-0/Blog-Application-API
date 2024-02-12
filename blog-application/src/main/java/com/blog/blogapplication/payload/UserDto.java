package com.blog.blogapplication.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents a Data Transfer Object (DTO) for a user.
 * It contains information about the user's name, email, password, and a short about section.
 */
@NoArgsConstructor
@Setter
@Getter
public class UserDto {

  /** The unique identifier for the user. */
  private Integer id;

  /** The name of the user. */
  @NotEmpty
  @Size(min = 3, message = "Name must be of min 3 Characters")
  private String name;

  /** The email address of the user. */
  @NotEmpty
  @Email(message = "Email must be valid")
  private String email;

  /** The password of the user. */
  @NotEmpty
  @Size(min = 3, message = "Password is too Short")
  private String password;

  /** A short description about the user. */
  @NotEmpty
  private String about;
}
/*
* UserDTO has been created so that we don't expose our User entity directly to Services
* */