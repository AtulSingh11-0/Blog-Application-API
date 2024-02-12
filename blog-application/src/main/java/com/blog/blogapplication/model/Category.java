package com.blog.blogapplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a category in the blogging application.
 */
@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {

  /** The unique identifier for the category. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer categoryId;

  /** The title of the category. */
  @Column(name = "title", length = 100, nullable = false)
  private String categoryTitle;

  /** The description of the category. */
  @Column(name = "description")
  private String categoryDescription;

  /** The list of posts associated with the category. */
  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Post> posts = new ArrayList<>();
}
