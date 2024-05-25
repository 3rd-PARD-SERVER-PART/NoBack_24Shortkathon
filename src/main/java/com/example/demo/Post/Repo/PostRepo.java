package com.example.demo.Post.Repo;

import com.example.demo.Post.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p ORDER BY (p.likeCount1 + p.likeCount2 + p.likeCount3) DESC LIMIT 5")
    List<Post> findTop5ByOrderByLikeSumDesc();

}
