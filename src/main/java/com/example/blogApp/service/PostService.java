package com.example.blogApp.service;

import com.example.blogApp.dto.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);

    List<PostDTO> getAllPosts();

    PostDTO getPostById(Long id);

    PostDTO updatePost(Long id, PostDTO postDTO);

    void deletePost(Long id);
}
