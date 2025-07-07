package com.example.blogApp.controller;

import com.example.blogApp.dto.CommentDTO;
import com.example.blogApp.models.Comment;
import com.example.blogApp.service.CommentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/posts")
public class CommentController {

    private final CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{postId}/comments")
    public List<CommentDTO> getComments(@PathVariable Long postId) {
        return commentService.getComments(postId);
    }

    @PostMapping("/{postId}/comments")
    public String addComment(@PathVariable Long postId, @RequestBody CommentDTO comment) {
        return commentService.createComment(postId, comment);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public String deleteComment(@PathVariable Long postId, @PathVariable Integer commentId) {
        return commentService.deleteComment(postId, commentId);
    }

    @PutMapping("/{postId}/comments/{commentId}")
    public Comment editComment(
            @PathVariable Long postId,
            @PathVariable Integer commentId,
            @RequestBody Comment comment) {
        return commentService.editComment(postId, commentId, comment);
    }
}
