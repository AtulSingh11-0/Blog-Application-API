package com.blog.blogapplication.service.impl;

import com.blog.blogapplication.exception.ResourceNotFoundException;
import com.blog.blogapplication.model.Comment;
import com.blog.blogapplication.model.Post;
import com.blog.blogapplication.model.User;
import com.blog.blogapplication.payload.CommentDto;
import com.blog.blogapplication.repo.CommentRepo;
import com.blog.blogapplication.repo.PostRepo;
import com.blog.blogapplication.repo.UserRepo;
import com.blog.blogapplication.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Implementation of the {@link CommentService} interface providing methods to manage comments in the blogging application.
 */
@Service
public class CommentImpl implements CommentService {

  /** The repository for accessing comment data. */
  @Autowired
  private CommentRepo commentRepo;

  /** The repository for accessing user data. */
  @Autowired
  private UserRepo userRepo;

  /** The repository for accessing post data. */
  @Autowired
  private PostRepo postRepo;

  /** The ModelMapper for mapping between entities and DTOs. */
  @Autowired
  private ModelMapper modelMapper;

  /**
   * Creates a new comment.
   *
   * @param commentDto The DTO containing comment information.
   * @param userId     The ID of the user who created the comment.
   * @param postId     The ID of the post to which the comment is associated.
   * @return CommentDto The DTO representing the created comment.
   * @throws ResourceNotFoundException If the user or post is not found.
   */
  @Override
  public CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId) {
    User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
    Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

    Comment comment = this.modelMapper.map(commentDto, Comment.class);
    comment.setUser(user);
    comment.setPost(post);
    comment.setDateAdded(new Date());

    Comment savedComment = this.commentRepo.save(comment);

    return this.modelMapper.map(savedComment, CommentDto.class);
  }

  /**
   * Updates an existing comment.
   *
   * @param commentDto The DTO containing updated comment information.
   * @param commentId  The ID of the comment to update.
   * @return CommentDto The DTO representing the updated comment.
   * @throws ResourceNotFoundException If the comment is not found.
   */
  @Override
  public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
    Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment"
        , "comment id", commentId));

    comment.setCommentBody(commentDto.getCommentBody());

    Comment updatedComment = this.commentRepo.save(comment);
    return this.modelMapper.map(updatedComment, CommentDto.class);
  }

  /**
   * Deletes a comment by its ID.
   *
   * @param commentId The ID of the comment to delete.
   * @throws ResourceNotFoundException If the comment is not found.
   */
  @Override
  public void deleteComment(Integer commentId) {
    Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment"
        , "comment id", commentId));
    this.commentRepo.delete(comment);
  }
}
