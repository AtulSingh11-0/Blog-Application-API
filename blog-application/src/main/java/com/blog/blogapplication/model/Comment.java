package com.blog.blogapplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comments")
@NoArgsConstructor
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer commentId;

  @Column(name = "commentBody", length = 10000, nullable = false)
  private String commentBody;

  @ManyToOne
  private User user;

  @ManyToOne
  private Post post;
}
