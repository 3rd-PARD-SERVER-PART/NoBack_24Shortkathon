package com.example.demo.Post.Entity;

import com.example.demo.Comment.DTO.CommentReadDTO;
import com.example.demo.Comment.Entity.Comment;
import com.example.demo.User.Entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;
    private String keyword;
    private String imageUrl;
    private String text;
    private int likeCount1;
    private int likeCount2;
    private int likeCount3;

    // Post와 User 간의 다대일 관계 설정
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // user_id 컬럼을 외래키로 사용하고, null 값을 허용하지 않음
    private User myUser; // 해당 게시물을 생성한 사용자

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;


}