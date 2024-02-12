package com.blog.blogapplication.repo;

import com.blog.blogapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface represents the repository for managing users in the blogging application.
 * It extends JpaRepository, providing CRUD operations for the User entity.
 */
public interface UserRepo extends JpaRepository<User, Integer> {
}
