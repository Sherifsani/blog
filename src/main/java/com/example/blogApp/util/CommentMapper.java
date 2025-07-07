package com.example.blogApp.util;


import com.example.blogApp.dto.CommentDTO;
import com.example.blogApp.models.Comment;
import com.example.blogApp.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    private final UserService userService;

    public CommentMapper(UserService userService) {
        this.userService = userService;
    }

    public CommentDTO toCommentDTO(Comment comment){
        return new CommentDTO(comment.getUser().getUsername(), comment.getBody());
    }

    public Comment toEntity(CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setBody(commentDTO.getComment());
        comment.setUser(userService.findUserByName(commentDTO.getUsername()));
        return comment;
    }
}
