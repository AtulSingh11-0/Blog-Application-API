package com.blog.blogapplication.controller;

import com.blog.blogapplication.payload.ApiResponse;
import com.blog.blogapplication.payload.PostDto;
import com.blog.blogapplication.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
  @Autowired
  private PostService postService;

  @PostMapping("/user/{userId}/category/{categoryId}/posts")
  public ResponseEntity<PostDto> createPost(@Valid
                                            @RequestBody PostDto postDto,
                                            @PathVariable Integer userId,
                                            @PathVariable Integer categoryId) {
    PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
    return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
  }

  @PutMapping("/post/{postId}")
  public ResponseEntity<PostDto> updatePost (@Valid
                                              @RequestBody PostDto postDto,
                                              @PathVariable Integer postId) {
    PostDto updatedPost = this.postService.updatePost(postDto, postId);
    return new ResponseEntity<>(updatedPost, HttpStatus.OK);
  }

  @DeleteMapping("/post/{postId}")
  public ResponseEntity<ApiResponse> deletePost (@Valid
                                                 @PathVariable Integer postId) {
    this.postService.deletePost(postId);
    return new ResponseEntity<>(new ApiResponse("Post Deleted Successfully", true), HttpStatus.OK);
  }

  @GetMapping("/post/{postId}")
  public ResponseEntity<PostDto> getPost (@Valid
                                          @PathVariable Integer postId) {
    PostDto post = this.postService.getPostById(postId);
    return new ResponseEntity<>(post, HttpStatus.OK);
  }

  @GetMapping("/posts")
  public ResponseEntity<List<PostDto>> getAllPosts() {
    List<PostDto> posts = this.postService.getAllPosts();
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

  @GetMapping("/user/{userId}/posts")
  public ResponseEntity<List<PostDto>> getPostsByUser (@Valid
                                                       @PathVariable Integer userId) {
    List<PostDto> posts = this.postService.getPostsByUser(userId);
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

  @GetMapping("/category/{categoryId}/posts")
  public ResponseEntity<List<PostDto>> getPostsByCategory (@Valid
                                                       @PathVariable Integer categoryId) {
    List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }
}
