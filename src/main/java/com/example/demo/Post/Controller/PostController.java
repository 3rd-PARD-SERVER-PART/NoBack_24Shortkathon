package com.example.demo.Post.Controller;

import com.example.demo.Post.DTO.PostCreateDTO;
import com.example.demo.Post.DTO.PostReadDTO;
import com.example.demo.Post.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping("")
    public String createPost(@RequestBody PostCreateDTO dto) {
        postService.createPost(dto);
        return "게시물 생성됨";
    }

    @GetMapping("/all")
    public List<PostReadDTO> findAllPosts() {
        return postService.findAll();
    }

    @GetMapping("/user/{id}")
    public List<PostReadDTO> findAllPostsByUserId(@PathVariable Long id) {
        return postService.findAllByUserId(id);
    }

    @GetMapping("/{id}")
    public PostReadDTO findById(@PathVariable Long id) {
        return postService.findById(id);
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deleteById(id);
        return "게시물 삭제";
    }

    @GetMapping("/top5")
    public List<PostReadDTO> getTop5PostsByLikeSum() {
        return postService.findTop5ByLikeSum();
    }
}
