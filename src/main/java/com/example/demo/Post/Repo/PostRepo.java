package com.example.demo.Post.Repo;

import com.example.demo.Post.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {
}
