package com.blog.blogapplication.repo;

import com.blog.blogapplication.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface represents the repository for managing categories in the blogging application.
 * It extends JpaRepository, providing CRUD operations for the Category entity.
 */
public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
