package com.example.demo.User.Entity;

import com.example.demo.Post.Entity.Post;
import com.example.demo.User.DTO.UserCreateDTO;
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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname")
    private String name;

    public static User toEntity(UserCreateDTO dto) {
        return User.builder()
                .name(dto.getNickname())
                .build();
    }

    @OneToMany(mappedBy = "myUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts; // 게시물 목록
}