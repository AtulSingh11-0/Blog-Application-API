package com.blog.blogapplication.service;

import com.blog.blogapplication.payload.CommentDto;

public interface CommentService {
  CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId);

  CommentDto updateComment(CommentDto commentDto, Integer commentId);

  void deleteComment(Integer commentId);
}
