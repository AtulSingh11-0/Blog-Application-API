package com.blog.blogapplication.service.impl;

import com.blog.blogapplication.exception.ResourceNotFoundException;
import com.blog.blogapplication.model.Category;
import com.blog.blogapplication.payload.CategoryDto;
import com.blog.blogapplication.repo.CategoryRepo;
import com.blog.blogapplication.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link CategoryService} interface providing methods to manage categories in the blogging application.
 */
@Service
public class CategoryImpl implements CategoryService {

  /** The repository for accessing category data. */
  @Autowired
  private CategoryRepo categoryRepo;

  /** The ModelMapper for mapping between entities and DTOs. */
  @Autowired
  private ModelMapper modelMapper;

  /**
   * Creates a new category.
   *
   * @param categoryDto The DTO containing category information.
   * @return CategoryDto The DTO representing the created category.
   */
  @Override
  public CategoryDto createCategory(CategoryDto categoryDto) {
    Category category = this.modelMapper.map(categoryDto, Category.class);
    Category addedCategory = this.categoryRepo.save(category);
    return this.modelMapper.map(addedCategory, CategoryDto.class);
  }

  /**
   * Retrieves a category by its ID.
   *
   * @param id The ID of the category to retrieve.
   * @return CategoryDto The DTO representing the retrieved category.
   * @throws ResourceNotFoundException If the category is not found.
   */
  @Override
  public CategoryDto getCategoryById(Integer id) {
    Category category = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", id));
    return this.modelMapper.map(category, CategoryDto.class);
  }

  /**
   * Updates an existing category.
   *
   * @param categoryDto The DTO containing updated category information.
   * @param id          The ID of the category to update.
   * @return CategoryDto The DTO representing the updated category.
   * @throws ResourceNotFoundException If the category is not found.
   */
  @Override
  public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
    Category category = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", id));
    category.setCategoryTitle(categoryDto.getCategoryTitle());
    category.setCategoryDescription(categoryDto.getCategoryDescription());

    Category updatedCategory = this.categoryRepo.save(category);
    return this.modelMapper.map(updatedCategory, CategoryDto.class);
  }

  /**
   * Deletes a category by its ID.
   *
   * @param id The ID of the category to delete.
   * @throws ResourceNotFoundException If the category is not found.
   */
  @Override
  public void deleteCategory(Integer id) {
    Category category = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", id));
    this.categoryRepo.delete(category);
  }

  /**
   * Retrieves all categories.
   *
   * @return List<CategoryDto> The list of all categories.
   */
  @Override
  public List<CategoryDto> getAllCategories() {
    List<Category> categories = this.categoryRepo.findAll();
    return categories.stream().map(this::apply).collect(Collectors.toList());
  }

  /**
   * Maps a Category entity to a CategoryDto.
   *
   * @param category The Category entity to map.
   * @return CategoryDto The mapped CategoryDto.
   */
  private CategoryDto apply(Category category) {
    return this.modelMapper.map(category, CategoryDto.class);
  }
}
