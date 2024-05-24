package com.example.demo.Comment.Service;

import com.example.demo.Comment.DTO.CommentCreateDTO;
import com.example.demo.Comment.DTO.CommentReadDTO;
import com.example.demo.Comment.Entity.Comment;
import com.example.demo.Comment.Repo.CommentRepo;
import com.example.demo.Post.Repo.PostRepo;
import com.example.demo.User.Repo.UserRepo;
import com.example.demo.User.Entity.User;
import com.example.demo.Post.Entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepo commentRepo;
    private final UserRepo userRepo;
    private final PostRepo postRepo;

    public void createComment(CommentCreateDTO dto) {
        User user = userRepo.findById(dto.getUserId()).orElseThrow();
        Post post = postRepo.findById(dto.getPostId()).orElseThrow();
        Comment comment = Comment.builder()
                .user(user)
                .post(post)
                .comment(dto.getComment())
                .build();
        commentRepo.save(comment);
    }

    public CommentReadDTO findById(Long commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow();
        return CommentReadDTO.builder()
                .commentId(comment.getCommentId())
                .nickname(comment.getUser().getName())
                .comment(comment.getComment())
                .build();
    }

    public List<CommentReadDTO> findAll() {
        return commentRepo.findAll()
                .stream()
                .map(comment -> CommentReadDTO.builder()
                        .commentId(comment.getCommentId())
                        .nickname(comment.getUser().getName())
                        .comment(comment.getComment())
                        .build())
                .collect(Collectors.toList());
    }

    public void deleteById(Long commentId) {
        commentRepo.deleteById(commentId);
    }
}
