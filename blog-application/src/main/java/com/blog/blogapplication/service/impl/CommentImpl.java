package com.blog.blogapplication.service.impl;

import com.blog.blogapplication.exception.ResourceNotFoundException;
import com.blog.blogapplication.model.Comment;
import com.blog.blogapplication.model.Post;
import com.blog.blogapplication.payload.CommentDto;
import com.blog.blogapplication.repo.CommentRepo;
import com.blog.blogapplication.repo.PostRepo;
import com.blog.blogapplication.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentImpl implements CommentService {

  @Autowired
  private CommentRepo commentRepo;

  @Autowired
  private PostRepo postRepo;

  @Autowired
  private ModelMapper modelMapper;
  @Override
  public CommentDto createComment(CommentDto commentDto, Integer postId) {
    Post post = this.postRepo.findById(postId).orElseThrow( () -> new ResourceNotFoundException("Post", "post id", postId));

    Comment comment = this.modelMapper.map(commentDto, Comment.class);
    comment.setPost(post);
    comment.setDateAdded(new Date());

    Comment savedComment = this.commentRepo.save(comment);

    return this.modelMapper.map(savedComment, CommentDto.class);
  }

  @Override
  public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
    Comment comment = this.commentRepo.findById(commentId).orElseThrow( () -> new ResourceNotFoundException("Comment"
        , "comment id", commentId));

    comment.setCommentBody(commentDto.getCommentBody());

    Comment updatedComment = this.commentRepo.save(comment);
    return this.modelMapper.map(updatedComment, CommentDto.class);
  }

  @Override
  public void deleteComment(Integer commentId) {
    Comment comment = this.commentRepo.findById(commentId).orElseThrow( () -> new ResourceNotFoundException("Comment"
        , "comment id", commentId));
    this.commentRepo.delete(comment);
  }
}
