package com.blog.blogapplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Represents a comment entity in the blogging application.
 */
@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
public class Comment {

  /**
   * The unique identifier for the comment.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer commentId;

  /**
   * The body of the comment.
   */
  @Column(name = "commentBody", length = 10000, nullable = false)
  private String commentBody;

  /**
   * The date when the comment was added.
   */
  private Date dateAdded;

  /**
   * The user who posted the comment.
   */
  @ManyToOne
  private User user;

  /**
   * The post to which the comment belongs.
   */
  @ManyToOne
  @JoinColumn(name = "post_id")
  private Post post;
}
