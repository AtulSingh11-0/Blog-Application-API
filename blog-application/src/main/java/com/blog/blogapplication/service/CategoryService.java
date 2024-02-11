package com.blog.blogapplication.service;

import com.blog.blogapplication.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
//  create
  CategoryDto createCategory(CategoryDto categoryDto);

//  get by ID
  CategoryDto getCategoryById(Integer id);

//  update
  CategoryDto updateCategory(CategoryDto categoryDto, Integer id);

//  delete
  void deleteCategory(Integer id);

//  get all
  List<CategoryDto> getAllCategories();
}
