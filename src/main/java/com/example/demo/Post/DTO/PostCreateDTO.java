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
    // like는 나중에 생성됨.
//    private int like;
//    private String comment;
}
