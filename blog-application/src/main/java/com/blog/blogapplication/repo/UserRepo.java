package com.blog.blogapplication.repo;

import com.blog.blogapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
