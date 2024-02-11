package com.blog.blogapplication.service.impl;

import com.blog.blogapplication.exception.ResourceNotFoundException;
import com.blog.blogapplication.model.Category;
import com.blog.blogapplication.model.Post;
import com.blog.blogapplication.model.User;
import com.blog.blogapplication.payload.PostDto;
import com.blog.blogapplication.repo.CategoryRepo;
import com.blog.blogapplication.repo.PostRepo;
import com.blog.blogapplication.repo.UserRepo;
import com.blog.blogapplication.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
   * @param postDto it's the post data transfer object which has stored the data provided by the user
   * @param userId  it will help in saving the post based on the ID of the user
   * @param categoryId  it will help in assigning the post its category based on the category ID
   * @return returns the saved post after mapping the post object to object of class type PostDto
   */
  @Override
  public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
    /*
    * getting the respective user and category via there respective ID's
    * so that the post will be created based on the userId and categoryId
    * */
    User user = this.userRepo.findById(userId).orElseThrow( () -> new ResourceNotFoundException("User", "User id", userId));
    Category category = this.categoryRepo.findById(categoryId).orElseThrow( () -> new ResourceNotFoundException("Category",
        "Category id", categoryId));

    /*
    * mapping the postDto object to an object of class Post type,
    * and then we set the default image, date, user and category for the post
    * */
    Post post = this.modelMapper.map(postDto, Post.class);
    post.setPostImage("default.png");
    post.setDateAdded(new Date());
    post.setUser(user);
    post.setCategory(category);

    /*
    * then we have to save the post object and store it in another object of type Post
    * */
    Post savedPost = this.postRepo.save(post);

    /*
    * now what we have to do is map the object of type Post class to an object of type PostDto class and return it
    * */
    return this.modelMapper.map(savedPost, PostDto.class);
  }

  @Override
  public PostDto updatePost(PostDto postDto, Integer postId) {
    return null;
  }

  @Override
  public void deletePost(Integer postId) {
  }

  @Override
  public PostDto getPostById(Integer postId) {
    return null;
  }

  @Override
  public List<PostDto> getAllPosts() {
    return null;
  }

  @Override
  public List<PostDto> getPostsByUser(Integer userId) {
    return null;
  }

  @Override
  public List<PostDto> getPostsByCategory(Integer categoryId) {
    return null;
  }

  @Override
  public List<PostDto> searchPosts(String searchQuery) {
    return null;
  }
}
