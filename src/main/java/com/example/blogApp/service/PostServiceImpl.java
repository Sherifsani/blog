package com.example.blogApp.service;

import com.example.blogApp.dto.PostDTO;
import com.example.blogApp.exception.PostNotFoundException;
import com.example.blogApp.exception.UserNotFoundException;
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
    public PostDTO createPost(PostDTO postDTO) {
        try {
            Post post = postMapper.toEntity(postDTO);
            Post savedPost = postRepository.save(post);
            return postMapper.toDTO(savedPost);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("User '" + postDTO.getUsername()
                    + "' does not exist. Please create the user first or use an existing username.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to create post: " + e.getMessage(), e);
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
        Post targetPost = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + id));
        return postMapper.toDTO(targetPost);
    }

    @Override
    public PostDTO updatePost(Long id, PostDTO postDTO) {
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + id));

        existingPost.setTitle(postDTO.getTitle());
        existingPost.setContent(postDTO.getContent());
        existingPost.setTimeUpdated(LocalDateTime.now());

        Post updatedPost = postRepository.save(existingPost);
        return postMapper.toDTO(updatedPost);
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + id));
        postRepository.delete(post);
    }

}
