package com.blog.blogapplication.repo;

import com.blog.blogapplication.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing comment data in the database.
 * Extends JpaRepository to inherit CRUD operations for the Comment entity.
 */
public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
