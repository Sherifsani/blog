package com.example.blogApp.util;

import com.example.blogApp.dto.PostDTO;
import com.example.blogApp.models.Post;
import com.example.blogApp.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    private final UserService userService;

    public PostMapper(UserService userService) {
        this.userService = userService;
    }

    public PostDTO toDTO(Post post) {
        String username = post.getUser() != null ? post.getUser().getUsername() : "Anonymous";
        return new PostDTO(post.getComments().size(), post.getContent(), post.getTitle() , username);
    }

    public Post toEntity(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setUser(userService.findUserByName(postDTO.getUsername()));
        return post;
    }
}
