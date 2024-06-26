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
        userService.createUser(dto);
        return "유저 들어옴";
    }

    @GetMapping("")
    public List<UserReadDTO> findAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserReadDTO findById(@PathVariable Long id) {
        return userService.findById(id);
    }


    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return "삭제됨";
    }
}