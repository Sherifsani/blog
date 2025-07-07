package com.example.blogApp.service;

import com.example.blogApp.dto.CommentDTO;
import com.example.blogApp.models.Comment;

import java.util.List;

public interface CommentService {
    List<CommentDTO> getComments(Long postId);
    String createComment(Long postId, CommentDTO commentDTO);
    String deleteComment(Long postId, Integer commentId);
    Comment editComment(Long postId,Integer commentId, Comment comment);
}
