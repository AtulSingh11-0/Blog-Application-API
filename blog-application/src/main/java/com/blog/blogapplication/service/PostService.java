package com.blog.blogapplication.service;

import com.blog.blogapplication.payload.PostDto;

import java.util.List;

public interface PostService {
//  create
  PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

  //  update
  PostDto updatePost(PostDto postDto, Integer postId);

//  delete
  void deletePost(Integer postId);

//  get by ID
  PostDto getPostById(Integer postId);

//  get all
  List<PostDto> getAllPosts();

//  get Posts by userID
  List<PostDto> getPostsByUser(Integer userId);

//  get Posts by categoryID
  List<PostDto> getPostsByCategory(Integer categoryId);

//  search Posts
  List<PostDto> searchPosts(String searchQuery);
}
