package com.blog.blogapplication.service;

import com.blog.blogapplication.payload.UserDto;
import java.util.List;

/**
 * This interface defines the contract for user-related operations.
 */
public interface UserService {

  /**
   * Creates a new user.
   *
   * @param userDto The DTO containing user information.
   * @return UserDto The DTO of the created user.
   */
  UserDto createUser(UserDto userDto);

  /**
   * Updates an existing user.
   *
   * @param userDto The DTO containing updated user information.
   * @param id      The ID of the user to update.
   * @return UserDto The DTO of the updated user.
   */
  UserDto updateUser(UserDto userDto, Integer id);

  /**
   * Retrieves a user by their ID.
   *
   * @param id The ID of the user to retrieve.
   * @return UserDto The DTO of the retrieved user.
   */
  UserDto getUserById(Integer id);

  /**
   * Deletes a user by their ID.
   *
   * @param id The ID of the user to delete.
   */
  void deleteUser(Integer id);

  /**
   * Retrieves all users.
   *
   * @return List<UserDto> A list containing DTOs of all users.
   */
  List<UserDto> getAllUsers();
}
