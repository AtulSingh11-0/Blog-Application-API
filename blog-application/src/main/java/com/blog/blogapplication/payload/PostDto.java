package com.blog.blogapplication.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
  private Integer postId;

  @NotBlank
  private String postTitle;

  @NotBlank
  private String postContent;
}