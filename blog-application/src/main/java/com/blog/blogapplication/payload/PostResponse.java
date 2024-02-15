package com.blog.blogapplication.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Represents a response containing a list of posts along with pagination information.
 */
@Setter
@Getter
@NoArgsConstructor
public class PostResponse {

  /** The list of posts. */
  private List<PostDto> posts;

  /** The current page number. */
  private int pageNumber;

  /** The size of each page. */
  private int pageSize;

  /** The total number of pages. */
  private int totalPage;

  /** The total number of elements/posts. */
  private long totalElements;

  /** Indicates whether it's the last page. */
  private boolean lastPage;
}
