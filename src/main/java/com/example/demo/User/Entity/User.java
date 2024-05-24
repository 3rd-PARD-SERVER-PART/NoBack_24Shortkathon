package com.example.demo.User.Entity;

import com.example.demo.User.DTO.UserCreateDTO;
import com.example.demo.User.DTO.UserReadDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public void update(UserReadDTO dto) {
        this.name = dto.getName();
    }
}

