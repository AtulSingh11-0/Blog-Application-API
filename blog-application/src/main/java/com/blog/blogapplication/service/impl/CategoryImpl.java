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

@Service
public class CategoryImpl implements CategoryService {
  @Autowired
  private CategoryRepo categoryRepo;
  @Autowired
  private ModelMapper modelMapper;

  @Override
  public CategoryDto createCategory(CategoryDto categoryDto) {
    Category category = this.modelMapper.map(categoryDto, Category.class);
    Category addedCategory = this.categoryRepo.save(category);
    return this.modelMapper.map(addedCategory, CategoryDto.class);
  }

  @Override
  public CategoryDto getCategoryById(Integer id) {
    Category category = this.categoryRepo.findById(id).orElseThrow( () -> new ResourceNotFoundException("Category",
        "Category id", id));
    return this.modelMapper.map(category, CategoryDto.class);
  }

  @Override
  public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
    Category category = this.categoryRepo.findById(id).orElseThrow( () -> new ResourceNotFoundException("Category",
        "Category id", id));
    category.setCategoryTitle(categoryDto.getCategoryTitle());
    category.setCategoryDescription(categoryDto.getCategoryDescription());

    Category updatedCategory = this.categoryRepo.save(category);
    return this.modelMapper.map(updatedCategory, CategoryDto.class);
  }

  @Override
  public void deleteCategory(Integer id) {
    Category category = this.categoryRepo.findById(id).orElseThrow( () -> new ResourceNotFoundException("Category",
        "Category id", id));
    this.categoryRepo.delete(category);
  }

  @Override
  public List<CategoryDto> getAllCategories() {
    List<Category> categories = this.categoryRepo.findAll();
    return categories.stream().map(this::apply).collect(Collectors.toList());
   }
   /*
   * this method maps the category object to an object of CategoryDto type
   * */
  private CategoryDto apply(Category category) {
    return this.modelMapper.map(category, CategoryDto.class);
  }
}