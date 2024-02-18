package com.blog.blogapplication.controller;

import com.blog.blogapplication.payload.ApiResponse;
import com.blog.blogapplication.payload.CommentDto;
import com.blog.blogapplication.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class CommentController {

  @Autowired
  private CommentService commentService;

  @PostMapping("users/{userId}/posts/{postId}/comment")
  public ResponseEntity<CommentDto> createComment(
      @Valid @RequestBody CommentDto commentDto,
      @PathVariable Integer userId,
      @PathVariable Integer postId
  ) {
    CommentDto comment = this.commentService.createComment(commentDto, userId, postId);
    return new ResponseEntity<>(comment, HttpStatus.CREATED);
  }

  @PutMapping("/comments/{commentId}")
  public ResponseEntity<CommentDto> updateComment(
      @RequestBody CommentDto commentDto,
      @PathVariable Integer commentId
  ) {
    CommentDto updatedComment = this.commentService.updateComment(commentDto, commentId);
    return new ResponseEntity<>(updatedComment, HttpStatus.OK);
  }

  @DeleteMapping("/comments/{commentId}")
  public ResponseEntity<ApiResponse> deleteComment(
      @PathVariable Integer commentId
  ) {
    this.commentService.deleteComment(commentId);
    return new ResponseEntity<>(new ApiResponse("Comment Deleted Successfully!!!!", true), HttpStatus.OK);
  }
}
