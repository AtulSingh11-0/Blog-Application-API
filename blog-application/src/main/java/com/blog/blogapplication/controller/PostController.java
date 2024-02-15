package com.blog.blogapplication.controller;

import com.blog.blogapplication.config.AppConstants;
import com.blog.blogapplication.payload.ApiResponse;
import com.blog.blogapplication.payload.PostDto;
import com.blog.blogapplication.payload.PostResponse;
import com.blog.blogapplication.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * This class represents the controller for handling post-related operations in the blogging application.
 * It defines REST endpoints for creating, updating, deleting, and retrieving posts, as well as searching for posts
 * based on different criteria such as user, category, or keyword.
 */
@RestController
@RequestMapping("/api/")
public class PostController {

  /** The service for handling post-related operations. */
  @Autowired
  private PostService postService;

  /**
   * Creates a new post for a specific user and category.
   *
   * @param postDto     The DTO containing post information.
   * @param userId      The ID of the user associated with the post.
   * @param categoryId  The ID of the category associated with the post.
   * @return ResponseEntity<PostDto> The response containing the created post DTO.
   */
  @PostMapping("/user/{userId}/category/{categoryId}/posts")
  public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,
                                            @PathVariable Integer userId,
                                            @PathVariable Integer categoryId) {
    PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
    return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
  }

  /**
   * Updates an existing post.
   *
   * @param postDto  The DTO containing updated post information.
   * @param postId   The ID of the post to update.
   * @return ResponseEntity<PostDto> The response containing the updated post DTO.
   */
  @PutMapping("/posts/{postId}")
  public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,
                                            @PathVariable Integer postId) {
    PostDto updatedPost = this.postService.updatePost(postDto, postId);
    return new ResponseEntity<>(updatedPost, HttpStatus.OK);
  }

  /**
   * Deletes a post by its ID.
   *
   * @param postId  The ID of the post to delete.
   * @return ResponseEntity<ApiResponse> The response indicating the success of the deletion operation.
   */
  @DeleteMapping("/posts/{postId}")
  public ResponseEntity<ApiResponse> deletePost(@Valid @PathVariable Integer postId) {
    this.postService.deletePost(postId);
    return new ResponseEntity<>(new ApiResponse("Post Deleted Successfully", true), HttpStatus.OK);
  }

  /**
   * Retrieves a post by its ID.
   *
   * @param postId  The ID of the post to retrieve.
   * @return ResponseEntity<PostDto> The response containing the retrieved post DTO.
   */
  @GetMapping("/posts/{postId}")
  public ResponseEntity<PostDto> getPost(@Valid @PathVariable Integer postId) {
    PostDto post = this.postService.getPostById(postId);
    return new ResponseEntity<>(post, HttpStatus.OK);
  }

  /**
   * Retrieves all posts with pagination and sorting options.
   *
   * @param pageNumber  The page number for pagination (default is provided).
   * @param pageSize    The page size for pagination (default is provided).
   * @param sortBy      The field to sort by (default is provided).
   * @param order       The sorting order (default is provided).
   * @return ResponseEntity<PostResponse> The response containing the paginated and sorted list of posts.
   */
  @GetMapping("/posts")
  public ResponseEntity<PostResponse> getAllPosts(
      @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
      @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
      @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
      @RequestParam(value = "order", defaultValue = AppConstants.ORDER, required = false) String order) {
    PostResponse posts = this.postService.getAllPosts(pageNumber, pageSize, sortBy, order);
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

  /**
   * Retrieves all posts by a specific user.
   *
   * @param userId  The ID of the user whose posts are to be retrieved.
   * @return ResponseEntity<List<PostDto>> The response containing the list of user's posts.
   */
  @GetMapping("/user/{userId}/posts")
  public ResponseEntity<List<PostDto>> getPostsByUser(@Valid @PathVariable Integer userId) {
    List<PostDto> posts = this.postService.getPostsByUser(userId);
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

  /**
   * Retrieves all posts belonging to a specific category.
   *
   * @param categoryId  The ID of the category whose posts are to be retrieved.
   * @return ResponseEntity<List<PostDto>> The response containing the list of category's posts.
   */
  @GetMapping("/category/{categoryId}/posts")
  public ResponseEntity<List<PostDto>> getPostsByCategory(@Valid @PathVariable Integer categoryId) {
    List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

  /**
   * Searches for posts based on a given keyword.
   *
   * @param keyword  The keyword to search for in post titles or content.
   * @return ResponseEntity<List<PostDto>> The response containing the list of posts matching the search criteria.
   */
  @GetMapping("/posts/search/{keyword}")
  public ResponseEntity<List<PostDto>> searchPosts(@Valid @PathVariable String keyword) {
    List<PostDto> result = this.postService.searchPosts(keyword);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
