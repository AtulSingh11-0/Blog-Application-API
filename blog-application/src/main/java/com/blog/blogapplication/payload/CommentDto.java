package com.blog.blogapplication.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

/**
 * Represents a Data Transfer Object (DTO) for a comment on a blog post.
 * Contains information about the unique identifier, author's username, comment body, and date of addition.
 */
@Getter
@Setter
@NoArgsConstructor
public class CommentDto {

  /** The unique identifier for the comment. */
  private Integer commentId;

  /** The username of the author of the comment. */
  private String userName;

  /** The body content of the comment. */
  @NotBlank
  private String commentBody;

  /** The date when the comment was added. */
  private Date dateAdded;
}
