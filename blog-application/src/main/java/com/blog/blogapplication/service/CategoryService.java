package com.blog.blogapplication.service;

import com.blog.blogapplication.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
  CategoryDto createCategory(CategoryDto categoryDto);
  CategoryDto getCategoryById(Integer id);
  CategoryDto updateCategory(CategoryDto categoryDto, Integer id);
  void deleteCategory(Integer id);
  List<CategoryDto> getAllCategories();
}
