package com.example.demo.User.Controller;

import com.example.demo.User.DTO.UserCreateDTO;
import com.example.demo.User.DTO.UserReadDTO;
import com.example.demo.User.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("")
    public String createUser(@RequestBody UserCreateDTO dto) {
        userService.createMember(dto);
        return "파드에 가입을 축하드립니다.";
    }

    @GetMapping("")
    public List<UserReadDTO> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserReadDTO findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PatchMapping("/{id}")
    public String updateById(@PathVariable Long id, @RequestBody UserReadDTO dto) {
        userService.update(id, dto);
        return "수정됨";
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return "삭제됨";
    }
}
