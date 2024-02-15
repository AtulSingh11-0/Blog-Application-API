package com.blog.blogapplication.repo;

import com.blog.blogapplication.model.Category;
import com.blog.blogapplication.model.Post;
import com.blog.blogapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * This interface represents the repository for managing blog posts.
 * It extends JpaRepository, providing CRUD operations for the Post entity.
 */
public interface PostRepo extends JpaRepository<Post, Integer> {

  /**
   * Retrieves a list of posts authored by a specific user.
   *
   * @param user The user whose posts are to be retrieved.
   * @return List<Post> The list of posts authored by the specified user.
   */
  List<Post> getByUser(User user);

  /**
   * Retrieves a list of posts belonging to a specific category.
   *
   * @param category The category whose posts are to be retrieved.
   * @return List<Post> The list of posts belonging to the specified category.
   */
  List<Post> getByCategory(Category category);

  /**
   * Searches for posts containing the specified keyword in their titles.
   *
   * @param keyword The keyword to search for in post titles.
   * @return List<Post> The list of posts containing the specified keyword in their titles.
   */
  List<Post> findByPostTitleContaining(String keyword);
}
