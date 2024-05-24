package com.example.demo.User.Service;

import com.example.demo.User.DTO.UserCreateDTO;
import com.example.demo.User.DTO.UserReadDTO;
import com.example.demo.User.Entity.User;
import com.example.demo.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;

    public void createMember(UserCreateDTO dto) {
        userRepo.save(User.toEntity(dto));
    }

    public UserReadDTO findById(Long id) {
        return UserReadDTO.toDTO(userRepo.findById(id).orElseThrow());
    }

    public List<UserReadDTO> findAll() {
        return userRepo.findAll()
                .stream()
                .map(UserReadDTO::toDTO)
                .collect(Collectors.toList());
    }

    public void update(Long id, UserReadDTO dto) {
        User user = userRepo.findById(id).orElseThrow();
        user.update(dto);
        userRepo.save(user);
    }

    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }
}
