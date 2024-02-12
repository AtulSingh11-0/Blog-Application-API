package com.blog.blogapplication.controller;

import com.blog.blogapplication.payload.ApiResponse;
import com.blog.blogapplication.payload.CategoryDto;
import com.blog.blogapplication.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class represents the controller for handling category-related operations in the blogging application.
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

  /** The service for handling category-related operations. */
  @Autowired
  private CategoryService categoryService;

  /**
   * Creates a new category.
   *
   * @param categoryDto The DTO (Data Transfer Object) containing category information.
   * @return ResponseEntity<CategoryDto> The response containing the created category DTO.
   */
  @PostMapping("/")
  public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
    CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);
    return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
  }

  /**
   * Retrieves a category by its ID.
   *
   * @param categoryId The ID of the category to retrieve.
   * @return ResponseEntity<CategoryDto> The response containing the retrieved category DTO.
   */
  @GetMapping("/{categoryId}")
  public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId) {
    CategoryDto category = this.categoryService.getCategoryById(categoryId);
    return new ResponseEntity<>(category, HttpStatus.OK);
  }

  /**
   * Updates an existing category.
   *
   * @param categoryDto The DTO containing updated category information.
   * @param categoryId  The ID of the category to update.
   * @return ResponseEntity<CategoryDto> The response containing the updated category DTO.
   */
  @PutMapping("/{categoryId}")
  public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
                                                    @PathVariable Integer categoryId) {
    CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, categoryId);
    return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
  }

  /**
   * Deletes a category by its ID.
   *
   * @param categoryId The ID of the category to delete.
   * @return ResponseEntity<ApiResponse> The response indicating the success of the deletion operation.
   */
  @DeleteMapping("/{categoryId}")
  public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
    this.categoryService.deleteCategory(categoryId);
    return new ResponseEntity<>(new ApiResponse("Category Deleted Successfully!!!!", true), HttpStatus.OK);
  }

  /**
   * Retrieves all categories.
   *
   * @return ResponseEntity<List<CategoryDto>> The response containing a list of all category DTOs.
   */
  @GetMapping("/")
  public ResponseEntity<List<CategoryDto>> getCategories() {
    List<CategoryDto> categories = this.categoryService.getAllCategories();
    return new ResponseEntity<>(categories, HttpStatus.OK);
  }
}
