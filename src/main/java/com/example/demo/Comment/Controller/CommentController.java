package com.example.demo.Comment.Controller;

import com.example.demo.Comment.DTO.CommentCreateDTO;
import com.example.demo.Comment.DTO.CommentReadDTO;
import com.example.demo.Comment.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("")
    public String createComment(@RequestBody CommentCreateDTO dto) {
        commentService.createComment(dto);
        return "댓글 생성됨";
    }

    @GetMapping("")
    public List<CommentReadDTO> findAllComments() {
        return commentService.findAll();
    }

    @GetMapping("/{id}")
    public CommentReadDTO findById(@PathVariable Long id) {
        return commentService.findById(id);
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id) {
        commentService.deleteById(id);
        return "댓글 삭제";
    }
}
