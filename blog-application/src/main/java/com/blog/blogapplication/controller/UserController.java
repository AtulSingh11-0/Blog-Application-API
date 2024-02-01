package com.blog.blogapplication.controller;

import com.blog.blogapplication.payload.UserDto;
import com.blog.blogapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;  

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

//  POST - create user
  @PostMapping("/")
  public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
    UserDto createUserDto = this.userService.createUser(userDto);
    return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
  }

//  PUT - update user
//  DELETE - delete user
//  GET - get user
}

/*
* UserController helps in handling the REST routes of the API
* */