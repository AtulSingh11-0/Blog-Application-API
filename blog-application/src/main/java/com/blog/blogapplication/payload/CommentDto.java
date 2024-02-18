package com.blog.blogapplication.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {

  private Integer commentId;

  private String userName;

  @NotBlank
  private String commentBody;

  private Date dateAdded;
}
