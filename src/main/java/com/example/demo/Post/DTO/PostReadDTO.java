package com.example.demo.Post.DTO;

import com.example.demo.Comment.DTO.CommentReadDTO;
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
    private int like;
//    private String comments;
    private List<CommentReadDTO> comments;
}
