package com.blog.blogapplication.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {
  private Integer categoryId;

  @NotBlank
  @Size(min = 3, message = "Category title must more than 3 Characters long")
  private String categoryTitle;

  @NotBlank
  @Size(min = 10, message = "Category description must be more than 10 Characters long")
  private String categoryDescription;
}
