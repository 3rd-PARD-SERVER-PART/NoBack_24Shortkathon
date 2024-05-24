package com.example.demo.Post.DTO;

import com.example.demo.Comment.DTO.CommentReadDTO;
import com.example.demo.User.DTO.UserReadDTO;
import com.example.demo.User.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostReadDTO {
    private Long postId;
    private String title;
    private String keyword;
    private String imageUrl;
    private String text;
    private int likeCount1;
    private int likeCount2;
    private int likeCount3;
    private UserReadDTO user;
    //    private String comments;
    private List<CommentReadDTO> comments;
}
