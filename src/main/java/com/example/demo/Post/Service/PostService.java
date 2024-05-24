package com.example.demo.Post.Service;

import com.example.demo.Post.DTO.PostCreateDTO;
import com.example.demo.Post.DTO.PostReadDTO;
import com.example.demo.Post.Entity.Post;
import com.example.demo.Post.Repo.PostRepo;
import com.example.demo.Comment.DTO.CommentReadDTO;
import com.example.demo.Comment.Repo.CommentRepo;
import com.example.demo.User.DTO.UserReadDTO;
import com.example.demo.User.Entity.User;
import com.example.demo.User.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public List<PostReadDTO> findAll(Long userId) {
        // User를 userId로 검색
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 해당 User의 게시물 목록 가져오기
        List<Post> posts = user.getPosts();

        // 게시물 목록을 PostReadDTO로 변환하여 반환
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


    public void deleteById(Long postId) {
        postRepo.deleteById(postId);
    }
}
