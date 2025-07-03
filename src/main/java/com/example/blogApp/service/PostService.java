package com.example.blogApp.service;

import com.example.blogApp.dto.PostDTO;
import com.example.blogApp.models.Post;

import java.util.List;

public interface PostService {
    String createPost(Post post);

    List<PostDTO> getAllPosts();

    PostDTO getPostById(Long id);

    Post updatePost(Long id, Post post);

    String deletePost(Long id);
}
