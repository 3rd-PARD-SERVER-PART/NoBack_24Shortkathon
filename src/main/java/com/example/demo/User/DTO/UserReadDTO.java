package com.example.demo.User.DTO;

import com.example.demo.User.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserReadDTO {
    private Long id;
    private String name;

    public static UserReadDTO toDTO(User user) {
        return UserReadDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}



