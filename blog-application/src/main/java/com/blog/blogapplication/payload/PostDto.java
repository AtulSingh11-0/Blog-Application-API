package com.blog.blogapplication.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
  private Integer postId;

  @NotBlank
  private String postTitle;

  @NotBlank
  private String postContent;

  private String postImage;

  private Date dateAdded;

  private UserDto user;

  private CategoryDto category;
}
