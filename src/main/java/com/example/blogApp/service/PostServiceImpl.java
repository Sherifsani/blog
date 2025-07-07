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
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public String createPost(PostDTO postDTO) {
        try {
            Post post = postMapper.toEntity(postDTO);
            postRepository.save(post);
            return "Post created successfully!";
        } catch (RuntimeException e) {
            if (e.getMessage().contains("User not found")) {
                return "Failed to create post: User '" + postDTO.getUsername()
                        + "' does not exist. Please create the user first or use an existing username.";
            }
            return "Failed to create post: " + e.getMessage();
        } catch (Exception e) {
            return "Failed to create post: " + e.getMessage();
        }
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(postMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostById(Long id) {
        Post targePost = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        return postMapper.toDTO(targePost);
    }

    @Override
    public Post updatePost(Long id, Post post) {
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        existingPost.setTitle(post.getTitle());
        existingPost.setContent(post.getContent());
        existingPost.setTimeUpdated(LocalDateTime.now());

        return postRepository.save(existingPost);
    }

    @Override
    public String deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        postRepository.delete(post);
        return "Post deleted successfully!";
    }

}
