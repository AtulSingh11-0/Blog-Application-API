package com.blog.blogapplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a user in the blogging application.
 */
@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User {

  /** The unique identifier for the user. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /** The name of the user. */
  @Column(nullable = false, length = 100)
  private String name;

  /** The email address of the user. */
  private String email;

  /** The password of the user. */
  private String password;

  /** A short description about the user. */
  private String about;

  /** The list of posts authored by the user. */
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Post> posts = new ArrayList<>();
}
