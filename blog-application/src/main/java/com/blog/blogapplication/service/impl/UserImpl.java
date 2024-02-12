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
 * This class implements the UserService interface to provide functionality for managing users in the blogging application.
 */
@Service
public class UserImpl implements UserService {

  /** The repository for accessing user data. */
  @Autowired
  private UserRepo userRepo;

  /** The ModelMapper for mapping between entities and DTOs. */
  @Autowired
  private ModelMapper modelMapper;

  @Override
  public UserDto createUser(UserDto userDto) {
    User user = this.modelMapper.map(userDto, User.class);
    User savedUser = this.userRepo.save(user);
    return this.modelMapper.map(savedUser, UserDto.class);
  }

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

  @Override
  public UserDto getUserById(Integer id) {
    User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", " id ", id));
    return this.modelMapper.map(user, UserDto.class);
  }

  @Override
  public void deleteUser(Integer id) {
    User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", " id ", id));
    this.userRepo.delete(user);
  }

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