package com.blog.blogapplication.controller;

import com.blog.blogapplication.payload.ApiResponse;
import com.blog.blogapplication.payload.UserDto;
import com.blog.blogapplication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

//  POST - create user
  @PostMapping("/")
  public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
    UserDto createUserDto = this.userService.createUser(userDto);
    return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
  }

//  PUT - update user
  @PutMapping("/{userId}")
  public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {
    UserDto updatedUser = this.userService.updateUser(userDto, userId);
    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
  }

//  DELETE - delete user
  @DeleteMapping("/{userId}")
  public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
    this.userService.deleteUser(userId);
    return new ResponseEntity<>(new ApiResponse("User Deleted successfully!!", true), HttpStatus.OK);
  }

//  GET - get all users
  @GetMapping("/")
  public ResponseEntity<List<UserDto>> getAllUsers() {
    return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
  }

  //  GET - get user by id
  @GetMapping("/{userId}")
  public ResponseEntity<UserDto> getAllUsers(@PathVariable Integer userId) {
    UserDto user = this.userService.getUserById(userId);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }
}
/*
* UserController helps in handling the REST routes of the API
* */