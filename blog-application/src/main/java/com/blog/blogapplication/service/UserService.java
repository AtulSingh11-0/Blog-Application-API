package com.blog.blogapplication.service;

import com.blog.blogapplication.payload.UserDto;

import java.util.List;

public interface UserService {
  UserDto createUser(UserDto userDto);
  UserDto updateUser(UserDto userDto, Integer id);
  UserDto getUserById(Integer id);
  void deleteUser(Integer id);
  List<UserDto> getAllUsers();
}

/*
* UserService interface which contains the method that we would require to create, update, delete, getById and getAll
*  users
* */