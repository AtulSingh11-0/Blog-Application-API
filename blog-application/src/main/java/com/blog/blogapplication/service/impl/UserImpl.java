package com.blog.blogapplication.service.impl;

import com.blog.blogapplication.exception.ResourceNotFoundException;
import com.blog.blogapplication.model.User;
import com.blog.blogapplication.payload.UserDto;
import com.blog.blogapplication.repo.UserRepo;
import com.blog.blogapplication.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserImpl implements UserService {
  @Autowired
  private UserRepo userRepo;
  @Autowired
  private ModelMapper modelMapper;

  @Override
  public UserDto createUser(UserDto userDto) {
    User user = this.dtoToUser(userDto);
    User savedUser = this.userRepo.save(user);
    return this.userToDto(savedUser);
  }

  @Override
  public UserDto updateUser(UserDto userDto, Integer id) {
    User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", " id ", id));

    user.setName(userDto.getName());
    user.setEmail(userDto.getEmail());
    user.setPassword(userDto.getPassword());
    user.setAbout(userDto.getAbout());

    User updatedUser = this.userRepo.save(user);
    return this.userToDto(updatedUser);
  }

  @Override
  public UserDto getUserById(Integer id) {
    User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", " id ", id));
    return this.userToDto(user);
  }

  @Override
  public void deleteUser(Integer id) {
    User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", " id ", id));
    this.userRepo.delete(user);
  }

  @Override
  public List<UserDto> getAllUsers() {
    List<User> users = this.userRepo.findAll();
    return users.stream().map(this::userToDto).collect(Collectors.toList());
    /*
    * this::userToDto -> this means
    * user -> this.userDto(user)
    * we have replaced lambda expression with method reference over here
    * */
  }

  private User dtoToUser(UserDto userDto) {
    return this.modelMapper.map(userDto, User.class);
  }

  private UserDto userToDto(User user) {
    return this.modelMapper.map(user, UserDto.class);
  }
}
/*
* The methods of the Services interface are implemented by this Class
* */