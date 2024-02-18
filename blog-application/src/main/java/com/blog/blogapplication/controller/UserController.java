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

/**
 * This class represents the controller for handling user-related operations in the blogging application.
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  /** The service for handling user-related operations. */
  @Autowired
  private UserService userService;

  /**
   * Creates a new user.
   *
   * @param userDto The DTO (Data Transfer Object) containing user information.
   * @return ResponseEntity<UserDto> The response containing the created user DTO.
   */
  @PostMapping("/")
  public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
    UserDto createUserDto = this.userService.createUser(userDto);
    return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
  }

  /**
   * Updates an existing user.
   *
   * @param userDto The DTO containing updated user information.
   * @param userId  The ID of the user to update.
   * @return ResponseEntity<UserDto> The response containing the updated user DTO.
   */
  @PutMapping("/{userId}")
  public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {
    UserDto updatedUser = this.userService.updateUser(userDto, userId);
    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
  }

  /**
   * Deletes a user by their ID.
   *
   * @param userId The ID of the user to delete.
   * @return ResponseEntity<ApiResponse> The response indicating the success of the deletion operation.
   */
  @DeleteMapping("/{userId}")
  public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
    this.userService.deleteUser(userId);
    return new ResponseEntity<>(new ApiResponse("User Deleted successfully!!", true), HttpStatus.OK);
  }

  /**
   * Retrieves all users.
   *
   * @return ResponseEntity<List<UserDto>> The response containing a list of all user DTOs.
   */
  @GetMapping("/")
  public ResponseEntity<List<UserDto>> getAllUsers() {
    return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
  }

  /**
   * Retrieves a user by their ID.
   *
   * @param userId The ID of the user to retrieve.
   * @return ResponseEntity<UserDto> The response containing the retrieved user DTO.
   */
  @GetMapping("/{userId}")
  public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) {
    UserDto user = this.userService.getUserById(userId);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }
}
