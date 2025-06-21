package com.example.blogApp.service;

import com.example.blogApp.models.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getComments(Long postId);
    String createComment(Long postId, Comment comment);
    String deleteComment(Long postId, Integer commentId);
    Comment editComment(Long postId,Integer commentId, Comment comment);
}
