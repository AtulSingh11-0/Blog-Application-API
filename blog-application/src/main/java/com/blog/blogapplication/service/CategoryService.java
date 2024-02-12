package com.blog.blogapplication.service;

import com.blog.blogapplication.payload.CategoryDto;

import java.util.List;

/**
 * This interface defines the contract for the service layer operations related to categories in the blogging application.
 */
public interface CategoryService {

  /**
   * Creates a new category.
   *
   * @param categoryDto The DTO (Data Transfer Object) containing category information.
   * @return CategoryDto The DTO representing the created category.
   */
  CategoryDto createCategory(CategoryDto categoryDto);

  /**
   * Retrieves a category by its ID.
   *
   * @param id The ID of the category to retrieve.
   * @return CategoryDto The DTO representing the retrieved category.
   */
  CategoryDto getCategoryById(Integer id);

  /**
   * Updates an existing category.
   *
   * @param categoryDto The DTO containing updated category information.
   * @param id          The ID of the category to update.
   * @return CategoryDto The DTO representing the updated category.
   */
  CategoryDto updateCategory(CategoryDto categoryDto, Integer id);

  /**
   * Deletes a category by its ID.
   *
   * @param id The ID of the category to delete.
   */
  void deleteCategory(Integer id);

  /**
   * Retrieves all categories.
   *
   * @return List<CategoryDto> A list containing DTOs representing all categories.
   */
  List<CategoryDto> getAllCategories();
}
