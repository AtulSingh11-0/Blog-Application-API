package com.blog.blogapplication.service.impl;

import com.blog.blogapplication.exception.ResourceNotFoundException;
import com.blog.blogapplication.model.User;
import com.blog.blogapplication.payload.UserDto;
import com.blog.blogapplication.repo.UserRepo;
import com.blog.blogapplication.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link UserService} interface providing methods to manage users in the blogging application.
 */
@Service
public class UserImpl implements UserService {

  /** The repository for accessing user data. */
  @Autowired
  private UserRepo userRepo;

  /** The ModelMapper for mapping between entities and DTOs. */
  @Autowired
  private ModelMapper modelMapper;

  /**
   * Creates a new user.
   *
   * @param userDto The DTO containing user information.
   * @return UserDto The DTO representing the created user.
   */
  @Override
  public UserDto createUser(UserDto userDto) {
    User user = this.modelMapper.map(userDto, User.class);
    User savedUser = this.userRepo.save(user);
    return this.modelMapper.map(savedUser, UserDto.class);
  }

  /**
   * Updates an existing user.
   *
   * @param userDto The DTO containing updated user information.
   * @param id      The ID of the user to update.
   * @return UserDto The DTO representing the updated user.
   * @throws ResourceNotFoundException If the user is not found.
   */
  @Override
  public UserDto updateUser(UserDto userDto, Integer id) {
    User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", " id ", id));

    user.setName(userDto.getName());
    user.setEmail(userDto.getEmail());
    user.setPassword(userDto.getPassword());
    user.setAbout(userDto.getAbout());

    User updatedUser = this.userRepo.save(user);
    return this.modelMapper.map(updatedUser, UserDto.class);
  }

  /**
   * Retrieves a user by their ID.
   *
   * @param id The ID of the user to retrieve.
   * @return UserDto The DTO representing the retrieved user.
   * @throws ResourceNotFoundException If the user is not found.
   */
  @Override
  public UserDto getUserById(Integer id) {
    User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", " id ", id));
    return this.modelMapper.map(user, UserDto.class);
  }

  /**
   * Deletes a user by their ID.
   *
   * @param id The ID of the user to delete.
   * @throws ResourceNotFoundException If the user is not found.
   */
  @Override
  public void deleteUser(Integer id) {
    User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", " id ", id));
    this.userRepo.delete(user);
  }

  /**
   * Retrieves all users.
   *
   * @return List<UserDto> The list of all users.
   */
  @Override
  public List<UserDto> getAllUsers() {
    List<User> users = this.userRepo.findAll();
    List<UserDto> list = new ArrayList<>();
    for (User user : users) {
      UserDto map = this.modelMapper.map(user, UserDto.class);
      list.add(map);
    }
    return list;
    /*
     * this::userToDto -> this means
     * user -> this.userDto(user)
     * we have replaced lambda expression with method reference over here
     * */
  }
}
