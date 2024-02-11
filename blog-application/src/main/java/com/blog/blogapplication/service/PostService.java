package com.blog.blogapplication.service;

import com.blog.blogapplication.model.Post;
import com.blog.blogapplication.payload.PostDto;

import java.util.List;

public interface PostService {
//  create
  Post createPost(PostDto postDto);

//  update
  Post updatePost(PostDto postDto, Integer postId);

//  delete
  void deletePost(Integer postId);

//  get by ID
  Post getPostById(Integer postId);

//  get all
  List<Post> getAllPosts();

//  get Posts by userID
  List<Post> getPostsByUser(Integer userId);

//  get Posts by categoryID
  List<Post> getPostsByCategory(Integer  categoryId);

//  search Posts
  List<Post> searchPosts(String searchQuery);
}
