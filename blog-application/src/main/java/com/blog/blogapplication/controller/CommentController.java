package com.blog.blogapplication.controller;

import com.blog.blogapplication.payload.ApiResponse;
import com.blog.blogapplication.payload.CommentDto;
import com.blog.blogapplication.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for managing comments in the blogging application.
 */
@RestController
@RequestMapping("/api/v1/")
public class CommentController {

  /** The service responsible for handling comment-related operations. */
  @Autowired
  private CommentService commentService;

  /**
   * Creates a new comment.
   *
   * @param commentDto The data transfer object containing the comment information.
   * @param userId     The ID of the user who creates the comment.
   * @param postId     The ID of the post to which the comment is added.
   * @return ResponseEntity<CommentDto> The response entity containing the created comment DTO.
   */
  @PostMapping("users/{userId}/posts/{postId}/comment")
  public ResponseEntity<CommentDto> createComment(
      @Valid @RequestBody CommentDto commentDto,
      @PathVariable Integer userId,
      @PathVariable Integer postId
  ) {
    CommentDto comment = this.commentService.createComment(commentDto, userId, postId);
    return new ResponseEntity<>(comment, HttpStatus.CREATED);
  }

  /**
   * Updates an existing comment.
   *
   * @param commentDto The data transfer object containing the updated comment information.
   * @param commentId  The ID of the comment to update.
   * @return ResponseEntity<CommentDto> The response entity containing the updated comment DTO.
   */
  @PutMapping("/comments/{commentId}")
  public ResponseEntity<CommentDto> updateComment(
      @RequestBody CommentDto commentDto,
      @PathVariable Integer commentId
  ) {
    CommentDto updatedComment = this.commentService.updateComment(commentDto, commentId);
    return new ResponseEntity<>(updatedComment, HttpStatus.OK);
  }

  /**
   * Deletes a comment.
   *
   * @param commentId The ID of the comment to delete.
   * @return ResponseEntity<ApiResponse> The response entity containing the deletion status.
   */
  @DeleteMapping("/comments/{commentId}")
  public ResponseEntity<ApiResponse> deleteComment(
      @PathVariable Integer commentId
  ) {
    this.commentService.deleteComment(commentId);
    return new ResponseEntity<>(new ApiResponse("Comment Deleted Successfully!!!!", true), HttpStatus.OK);
  }
}
