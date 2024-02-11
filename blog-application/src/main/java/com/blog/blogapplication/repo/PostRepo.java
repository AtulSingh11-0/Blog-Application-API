package com.blog.blogapplication.repo;

import com.blog.blogapplication.model.Category;
import com.blog.blogapplication.model.Post;
import com.blog.blogapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
  List<Post> getByUser(User user);
  List<Post> getByCategory(Category category);
}
