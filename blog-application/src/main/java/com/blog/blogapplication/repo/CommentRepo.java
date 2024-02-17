package com.blog.blogapplication.repo;

import com.blog.blogapplication.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
