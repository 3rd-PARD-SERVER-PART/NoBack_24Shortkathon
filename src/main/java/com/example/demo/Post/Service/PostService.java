package com.example.demo.Post.Service;

import com.example.demo.Post.DTO.PostCreateDTO;
import com.example.demo.Post.DTO.PostReadDTO;
import com.example.demo.Post.Entity.Post;
import com.example.demo.Post.Repo.PostRepo;
import com.example.demo.Comment.DTO.CommentReadDTO;
import com.example.demo.User.DTO.UserReadDTO;
import com.example.demo.User.Entity.User;
import com.example.demo.User.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepo postRepo;
    private final UserRepo userRepo;

    public void createPost(PostCreateDTO dto) {
        User user = userRepo.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Post post = Post.builder()
                .title(dto.getTitle())
                .keyword(dto.getKeyword())
                .imageUrl(dto.getImageUrl())
                .text(dto.getText())
                .myUser(user) // 사용자 설정
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
                .likeCount1(post.getLikeCount1())
                .likeCount2(post.getLikeCount2())
                .likeCount3(post.getLikeCount3())
                .comments(post.getComments().stream()
                        .map(comment -> CommentReadDTO.builder()
                                .commentId(comment.getCommentId())
                                .nickname(comment.getUser().getName())
                                .comment(comment.getComment())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    public List<PostReadDTO> findAllByUserId(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Post> posts = user.getPosts();

        return posts.stream()
                .map(post -> PostReadDTO.builder()
                        .postId(post.getPostId())
                        .title(post.getTitle())
                        .keyword(post.getKeyword())
                        .imageUrl(post.getImageUrl())
                        .text(post.getText())
                        .likeCount1(post.getLikeCount1())
                        .likeCount2(post.getLikeCount2())
                        .likeCount3(post.getLikeCount3())
                        .user(UserReadDTO.builder()
                                .id(user.getId())
                                .name(user.getName())
                                .build())
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

    public List<PostReadDTO> findAll() {
        List<Post> posts = postRepo.findAll();
        return posts.stream()
                .map(post -> PostReadDTO.builder()
                        .postId(post.getPostId())
                        .title(post.getTitle())
                        .keyword(post.getKeyword())
                        .imageUrl(post.getImageUrl())
                        .text(post.getText())
                        .likeCount1(post.getLikeCount1())
                        .likeCount2(post.getLikeCount2())
                        .likeCount3(post.getLikeCount3())
                        .user(UserReadDTO.builder()
                                .id(post.getMyUser().getId())
                                .name(post.getMyUser().getName())
                                .build())
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

    public List<PostReadDTO> findTop5ByLikeSum() {
        List<Post> topPosts = postRepo.findTop5ByOrderByLikeSumDesc();
        return topPosts.stream()
                .map(post -> PostReadDTO.builder()
                        .postId(post.getPostId())
                        .title(post.getTitle())
                        .keyword(post.getKeyword())
                        .imageUrl(post.getImageUrl())
                        .text(post.getText())
                        .likeCount1(post.getLikeCount1())
                        .likeCount2(post.getLikeCount2())
                        .likeCount3(post.getLikeCount3())
                        .user(UserReadDTO.builder()
                                .id(post.getMyUser().getId())
                                .name(post.getMyUser().getName())
                                .build())
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
}
