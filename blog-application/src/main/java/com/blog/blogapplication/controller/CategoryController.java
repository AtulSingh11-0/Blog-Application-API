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

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
  @Autowired
  private CategoryService categoryService;

  // POST - create Category
  @PostMapping("/")
  public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
    CategoryDto categoryDto1 = this.categoryService.createCategory(categoryDto);
    return new ResponseEntity<CategoryDto>(categoryDto1, HttpStatus.CREATED);
  }

  // GET - get Category by id
  @GetMapping("/{categoryId}")
  public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId) {
    CategoryDto category = this.categoryService.getCategoryById(categoryId);
    return new ResponseEntity<CategoryDto>(category, HttpStatus.OK);
  }

  // UPDATE - update Category
  @PutMapping("/{categoryId}")
  public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
                                                    @PathVariable Integer categoryId) {
    CategoryDto updatedUser = this.categoryService.updateCategory(categoryDto, categoryId);
    return new ResponseEntity<CategoryDto>(updatedUser, HttpStatus.OK);
  }

  // DELETE - delete Category
  @DeleteMapping("/{categoryId}")
  public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
    this.categoryService.deleteCategory(categoryId);
    return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully!!!!", true), HttpStatus.OK);
  }

  // GET - get0 Categories
  @GetMapping("/")
  public ResponseEntity<List<CategoryDto>> getCategories() {
    return new ResponseEntity<>(this.categoryService.getAllCategories(), HttpStatus.OK);
  }
}
