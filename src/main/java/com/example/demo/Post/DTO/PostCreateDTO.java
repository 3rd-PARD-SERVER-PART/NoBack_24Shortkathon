package com.example.demo.Post.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateDTO {
    private String title;
    private String keyword;
    private String imageUrl;
    private String text;
    private Long userId; // 사용자 ID 추가

    // like는 나중에 생성됨.
//    private int like;
//    private String comment;
}
