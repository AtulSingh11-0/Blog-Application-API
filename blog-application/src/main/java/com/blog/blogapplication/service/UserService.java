package com.blog.blogapplication.service;

import com.blog.blogapplication.payload.UserDto;

import java.util.List;

public interface UserService {
//  create
  UserDto createUser(UserDto userDto);

//  update
  UserDto updateUser(UserDto userDto, Integer id);

//  get by ID
  UserDto getUserById(Integer id);

//  delete
  void deleteUser(Integer id);

//  get all
  List<UserDto> getAllUsers();
}

/*
* UserService interface which contains the method that we would require to create, update, delete, getById and getAll
*  users
* */