package com.blog.blogapplication.service.impl;

import com.blog.blogapplication.exception.ResourceNotFoundException;
import com.blog.blogapplication.model.Category;
import com.blog.blogapplication.model.Post;
import com.blog.blogapplication.model.User;
import com.blog.blogapplication.payload.PostDto;
import com.blog.blogapplication.payload.PostResponse;
import com.blog.blogapplication.repo.CategoryRepo;
import com.blog.blogapplication.repo.PostRepo;
import com.blog.blogapplication.repo.UserRepo;
import com.blog.blogapplication.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementation of the {@link PostService} interface providing methods to manage posts in the blogging application.
 */
@Service
public class PostImpl implements PostService {
  @Autowired
  private PostRepo postRepo;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private CategoryRepo categoryRepo;

  /**
   * Creates a new post.
   *
   * @param postDto     The post data transfer object containing the post information.
   * @param userId      The ID of the user associated with the post.
   * @param categoryId  The ID of the category associated with the post.
   * @return PostDto    The DTO representing the created post.
   * @throws ResourceNotFoundException If the user or category is not found.
   */
  @Override
  public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
    User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));
    Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category",
        "Category id", categoryId));

    Post post = this.modelMapper.map(postDto, Post.class);
    post.setPostImage("default.png");
    post.setDateAdded(new Date());
    post.setUser(user);
    post.setCategory(category);

    Post savedPost = this.postRepo.save(post);

    return this.modelMapper.map(savedPost, PostDto.class);
  }

  /**
   * Updates an existing post.
   *
   * @param postDto  The DTO containing updated post information.
   * @param postId   The ID of the post to update.
   * @return PostDto The DTO representing the updated post.
   * @throws ResourceNotFoundException If the post is not found.
   */
  @Override
  public PostDto updatePost(PostDto postDto, Integer postId) {
    Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post id",
        postId));
    post.setPostTitle(postDto.getPostTitle());
    post.setPostContent(postDto.getPostContent());
    post.setPostImage(postDto.getPostImage());

    Post updatedPost = this.postRepo.save(post);
    return this.modelMapper.map(updatedPost, PostDto.class);
  }

  /**
   * Deletes a post by its ID.
   *
   * @param postId  The ID of the post to delete.
   * @throws ResourceNotFoundException If the post is not found.
   */
  @Override
  public void deletePost(Integer postId) {
    Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));
    this.postRepo.delete(post);
  }

  /**
   * Retrieves a post by its ID.
   *
   * @param postId  The ID of the post to retrieve.
   * @return PostDto The DTO representing the retrieved post.
   * @throws ResourceNotFoundException If the post is not found.
   */
  @Override
  public PostDto getPostById(Integer postId) {
    Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));
    return this.modelMapper.map(post, PostDto.class);
  }

  /**
   * Retrieves all posts with pagination and sorting options.
   *
   * @param pageNumber  The page number for pagination.
   * @param pageSize    The page size for pagination.
   * @param sortBy      The field to sort by.
   * @param order       The sorting order.
   * @return PostResponse The response containing the paginated and sorted list of posts.
   */
  @Override
  public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String order) {
    Sort sort = order.equalsIgnoreCase("ascending") ?
        Sort.by(sortBy).ascending() :
        Sort.by(sortBy).descending();

    Pageable page = PageRequest.of(pageNumber, pageSize, sort);
    Page<Post> posts = this.postRepo.findAll(page);
    List<PostDto> postDtos = new ArrayList<>();
    for (Post post : posts) {
      postDtos.add(this.modelMapper.map(post, PostDto.class));
    }
    PostResponse postResponse = new PostResponse();
    postResponse.setPosts(postDtos);
    postResponse.setPageNumber(posts.getNumber());
    postResponse.setPageSize(posts.getSize());
    postResponse.setTotalElements(posts.getTotalElements());
    postResponse.setTotalPage(posts.getTotalPages());
    postResponse.setLastPage(posts.isLast());

    return postResponse;
  }

  /**
   * Retrieves all posts by a specific user.
   *
   * @param userId  The ID of the user whose posts are to be retrieved.
   * @return List<PostDto> The list of user's posts.
   * @throws ResourceNotFoundException If the user is not found.
   */
  @Override
  public List<PostDto> getPostsByUser(Integer userId) {
    User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));

    List<Post> posts = this.postRepo.getByUser(user);
    List<PostDto> postDtos = new ArrayList<>();
    for (Post post : posts) {
      postDtos.add(this.modelMapper.map(post, PostDto.class));
    }
    return postDtos;
  }

  /**
   * Retrieves all posts belonging to a specific category.
   *
   * @param categoryId  The ID of the category whose posts are to be retrieved.
   * @return List<PostDto> The list of category's posts.
   * @throws ResourceNotFoundException If the category is not found.
   */
  @Override
  public List<PostDto> getPostsByCategory(Integer categoryId) {
    Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(
        "Category", "Category id", categoryId));

    List<Post> posts = this.postRepo.getByCategory(category);
    List<PostDto> postDtos = new ArrayList<>();
    for (Post post : posts) {
      postDtos.add(this.modelMapper.map(post, PostDto.class));
    }
    return postDtos;
  }

  /**
   * Searches for posts based on a given keyword.
   *
   * @param keyword  The keyword to search for in post titles or content.
   * @return List<PostDto> The list of posts matching the search criteria.
   */
  @Override
  public List<PostDto> searchPosts(String keyword) {
    List<Post> posts = this.postRepo.findByPostTitleContaining(keyword);
    List<PostDto> postDtos = new ArrayList<>();
    for (Post post : posts) {
      postDtos.add(this.modelMapper.map(post, PostDto.class));
    }
    return postDtos;
  }
}
