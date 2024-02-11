package com.blog.blogapplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "posts")
@Setter
@Getter
@NoArgsConstructor
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer postId;

  @Column(name = "title", nullable = false)
  private String postTitle;

  @Column(name = "content", length = 10000, nullable = false)
  private String postContent;
  private String postImage;
  private Date dateAdded;

  @ManyToOne
  private User user;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;
}
