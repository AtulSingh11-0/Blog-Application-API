package com.blog.blogapplication.service;

import com.blog.blogapplication.payload.PostDto;
import com.blog.blogapplication.payload.PostResponse;

import java.io.IOException;
import java.util.List;

/**
 * This interface defines the service layer methods for managing posts in the blogging application.
 */
public interface PostService {

  /**
   * Creates a new post.
   *
   * @param postDto     The DTO containing post information.
   * @param userId      The ID of the user associated with the post.
   * @param categoryId  The ID of the category associated with the post.
   * @return PostDto    The DTO representing the created post.
   * @throws IOException if an I/O error occurs.
   */
  PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) throws IOException;

  /**
   * Updates an existing post.
   *
   * @param postDto  The DTO containing updated post information.
   * @param postId   The ID of the post to update.
   * @return PostDto The DTO representing the updated post.
   */
  PostDto updatePost(PostDto postDto, Integer postId);

  /**
   * Deletes a post by its ID.
   *
   * @param postId  The ID of the post to delete.
   */
  void deletePost(Integer postId);

  /**
   * Retrieves a post by its ID.
   *
   * @param postId  The ID of the post to retrieve.
   * @return PostDto The DTO representing the retrieved post.
   */
  PostDto getPostById(Integer postId);

  /**
   * Retrieves all posts with pagination and sorting options.
   *
   * @param pageNumber  The page number for pagination.
   * @param pageSize    The page size for pagination.
   * @param sortBy      The field to sort by.
   * @param order       The sorting order.
   * @return PostResponse The response containing the paginated and sorted list of posts.
   */
  PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String order);

  /**
   * Retrieves all posts by a specific user.
   *
   * @param userId  The ID of the user whose posts are to be retrieved.
   * @return List<PostDto> The list of user's posts.
   */
  List<PostDto> getPostsByUser(Integer userId);

  /**
   * Retrieves all posts belonging to a specific category.
   *
   * @param categoryId  The ID of the category whose posts are to be retrieved.
   * @return List<PostDto> The list of category's posts.
   */
  List<PostDto> getPostsByCategory(Integer categoryId);

  /**
   * Searches for posts based on a given keyword.
   *
   * @param searchQuery  The keyword to search for in post titles or content.
   * @return List<PostDto> The list of posts matching the search criteria.
   */
  List<PostDto> searchPosts(String searchQuery);
}
