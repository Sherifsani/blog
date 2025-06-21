package com.example.blogApp.service;

import com.example.blogApp.models.Comment;
import com.example.blogApp.models.Post;
import com.example.blogApp.repository.CommentRepository;
import com.example.blogApp.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public List<Comment> getComments(Long postId){
        Post targetPost = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found!"));
        return targetPost.getComments();
    }

    @Override
    public String createComment(Long postId, Comment comment){
        Post targetPost = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        comment.setPost(targetPost);
        commentRepository.save(comment);
        return "Comment created successfully";
    }

    @Override
    public String deleteComment(Long postId, Integer commentId){
        Post targetPost = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
        if (!comment.getPost().getId().equals(targetPost.getId())) {
            throw new RuntimeException("Comment does not belong to the given post");
        }
        commentRepository.delete(comment);
        return "Comment deleted successfully.";
    }

    @Override
    public Comment editComment(Long postId,Integer commentId, Comment comment){
        Post targetPost = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        Comment targetComment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
        if (!targetComment.getPost().getId().equals(targetPost.getId())) {
            throw new RuntimeException("Comment does not belong to the given post");
        }
        targetComment.setBody(comment.getBody());
        commentRepository.save(targetComment);
        return targetComment;
    }
}
