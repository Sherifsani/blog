package com.example.blogApp.service;

import com.example.blogApp.dto.PostDTO;
import com.example.blogApp.models.Post;
import com.example.blogApp.repository.PostRepository;
import com.example.blogApp.util.PostMapper;

import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    @Override
    public String createPost(Post post) {
        postRepository.save(post);
        return "Post created successfully!";
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream().map(PostMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostById(Long id){
        Post targePost = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        return PostMapper.toDTO(targePost);
    }

    @Override
    public Post updatePost(Long id, Post post){
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        existingPost.setTitle(post.getTitle());
        existingPost.setContent(post.getContent());
        existingPost.setTimeUpdated(LocalDateTime.now());

        return postRepository.save(existingPost);
    }

    @Override
    public String deletePost(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        postRepository.delete(post);
        return "Post deleted successfully!";
    }

}
