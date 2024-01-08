package com.blog.blogapplication.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {
  private Integer id;
  private String name;
  private String email;
  private String password;
  private String about;
}

/*
* UserDTO has been created so that we don't expose our User entity directly to Services
* */