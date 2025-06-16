package com.example.blogApp.service;

import com.example.blogApp.models.Post;

import java.util.List;

public interface PostService {
    String createPost(Post post);

    List<Post> getAllPosts();

    Post getPostById(Long id);

    Post updatePost(Long id, Post post);

    String deletePost(Long id);
}
