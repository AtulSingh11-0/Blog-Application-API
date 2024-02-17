package com.blog.blogapplication.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a Data Transfer Object (DTO) for a blog post.
 * It contains information about the title, content, image, date of addition, associated user, and category of a post.
 */
@NoArgsConstructor
@Getter
@Setter
public class PostDto {

  /** The unique identifier for the post. */
  private Integer postId;

  /** The title of the post. */
  @NotBlank
  private String postTitle;

  /** The content of the post. */
  @NotBlank
  private String postContent;

  /** The image associated with the post. */
  private String postImage;

  /** The date when the post was added. */
  private Date dateAdded;

  /** The DTO representing the user who authored the post. */
  private UserDto user;

  /** The DTO representing the category to which the post belongs. */
  private CategoryDto category;

  private Set<CommentDto> comments = new HashSet<>();
}
