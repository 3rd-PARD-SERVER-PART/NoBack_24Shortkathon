package com.example.demo.Comment.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentReadDTO {
    private Long commentId;
    private String nickname;
    private String comment;
}
