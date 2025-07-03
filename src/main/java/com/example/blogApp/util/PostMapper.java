package com.example.blogApp.util;

import com.example.blogApp.dto.PostDTO;
import com.example.blogApp.models.Post;

public class PostMapper {
    public static PostDTO toDTO(Post post) {
        return new PostDTO(post.getTitle(), post.getContent());
                 
    }

    public static Post toEntity(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        // Assuming you have a method to find a user by username
        // post.setUser(userService.findByUsername(postDTO.getAuthor()));
        return post;
    }
}
