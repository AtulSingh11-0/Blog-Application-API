package com.blog.blogapplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a blog post in the blogging application.
 * Each post has a title, content, optional image, date of addition, associated user, and category.
 */
@Entity
@Table(name = "posts")
@Setter
@Getter
@NoArgsConstructor
public class Post {

  /** The unique identifier for the post. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer postId;

  /** The title of the post. */
  @Column(name = "title", nullable = false)
  private String postTitle;

  /** The content of the post. */
  @Column(name = "content", length = 10000, nullable = false)
  private String postContent;

  /** The image associated with the post. */
  private String postImage;

  /** The date when the post was added. */
  private Date dateAdded;

  /** The user who authored the post. */
  @ManyToOne
  private User user;

  /** The category to which the post belongs. */
  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<Comment> comments = new HashSet<>();;
}
