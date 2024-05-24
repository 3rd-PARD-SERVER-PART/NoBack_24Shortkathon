package com.example.demo.Comment.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateDTO {
    private Long userId;
    private Long postId;
    private String comment;
}
