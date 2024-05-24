package com.example.demo.Post.Service;

import com.example.demo.Post.DTO.PostCreateDTO;
import com.example.demo.Post.DTO.PostReadDTO;
import com.example.demo.Post.Entity.Post;
import com.example.demo.Post.Repo.PostRepo;
import com.example.demo.Comment.DTO.CommentReadDTO;
import com.example.demo.Comment.Repo.CommentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepo postRepo;
    private final CommentRepo commentRepo;

    public void createPost(PostCreateDTO dto) {
        Post post = Post.builder()
                .title(dto.getTitle())
                .keyword(dto.getKeyword())
                .imageUrl(dto.getImageUrl())
                .text(dto.getText())
                .build();
        postRepo.save(post);
    }

    public PostReadDTO findById(Long postId) {
        Post post = postRepo.findById(postId).orElseThrow();
        return PostReadDTO.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .keyword(post.getKeyword())
                .imageUrl(post.getImageUrl())
                .text(post.getText())
                .like(post.getLike())
                .comments(post.getComments().stream()
                        .map(comment -> CommentReadDTO.builder()
                                .commentId(comment.getCommentId())
                                .nickname(comment.getUser().getName())
                                .comment(comment.getComment())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    public List<PostReadDTO> findAll() {
        return postRepo.findAll()
                .stream()
                .map(post -> PostReadDTO.builder()
                        .postId(post.getPostId())
                        .title(post.getTitle())
                        .keyword(post.getKeyword())
                        .imageUrl(post.getImageUrl())
                        .text(post.getText())
                        .like(post.getLike())
                        .comments(post.getComments().stream()
                                .map(comment -> CommentReadDTO.builder()
                                        .commentId(comment.getCommentId())
                                        .nickname(comment.getUser().getName())
                                        .comment(comment.getComment())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }

    public void deleteById(Long postId) {
        postRepo.deleteById(postId);
    }
}
