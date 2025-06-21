package com.example.blogApp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue()
    private Integer id;

    @Column
    private String body;

    @ManyToOne()
    @JoinColumn(name = "blog_id")
    @JsonBackReference
    private Post post;

    public Comment() {
    }

    public Comment(String body, Post post) {
        this.body = body;
        this.post = post;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
