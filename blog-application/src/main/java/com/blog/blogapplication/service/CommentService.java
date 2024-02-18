package com.blog.blogapplication.service;

import com.blog.blogapplication.payload.CommentDto;

/**
 * This interface defines the operations for managing comments in the blogging application.
 */
public interface CommentService {

  /**
   * Creates a new comment associated with a user and a post.
   *
   * @param commentDto The DTO containing the information of the comment to be created.
   * @param userId     The ID of the user who created the comment.
   * @param postId     The ID of the post to which the comment is associated.
   * @return CommentDto The DTO representing the created comment.
   */
  CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId);

  /**
   * Updates an existing comment.
   *
   * @param commentDto The DTO containing the updated information of the comment.
   * @param commentId  The ID of the comment to be updated.
   * @return CommentDto The DTO representing the updated comment.
   */
  CommentDto updateComment(CommentDto commentDto, Integer commentId);

  /**
   * Deletes a comment by its ID.
   *
   * @param commentId The ID of the comment to be deleted.
   */
  void deleteComment(Integer commentId);
}
