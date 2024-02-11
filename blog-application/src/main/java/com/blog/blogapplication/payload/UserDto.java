package com.blog.blogapplication.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {
  private Integer id;

  @NotEmpty
  @Size(min = 3, message = "Name must be of min 3 Characters")
  private String name;

  @NotEmpty
  @Email(message = "Email must be valid")
  private String email;

  @NotEmpty
  @Size(min = 3, message = "Password is too Short")
  private String password;

  @NotEmpty
  private String about;
}

/*
* UserDTO has been created so that we don't expose our User entity directly to Services
* */