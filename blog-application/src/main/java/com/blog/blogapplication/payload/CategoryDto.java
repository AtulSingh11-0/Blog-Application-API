package com.blog.blogapplication.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents a Data Transfer Object (DTO) for a category.
 * It contains information about the category's title and description.
 */
@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {

  /** The unique identifier for the category. */
  private Integer categoryId;

  /** The title of the category. */
  @NotBlank
  @Size(min = 3, message = "Category title must be more than 3 characters long")
  private String categoryTitle;

  /** The description of the category. */
  @NotBlank
  @Size(min = 10, message = "Category description must be more than 10 characters long")
  private String categoryDescription;
}
