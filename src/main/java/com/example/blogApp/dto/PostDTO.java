package com.example.blogApp.dto;


public class PostDTO {
    private String username;
    private String title;
    private String content;
    private Integer numberOfComments;

    public Integer getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(Integer numberOfComments) {
        this.numberOfComments = numberOfComments;
    }


    public PostDTO() {
    }

    public PostDTO(Integer numberOfComments, String content, String title, String username) {
        this.content = content;
        this.title = title;
        this.username = username;
        this.numberOfComments = numberOfComments;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
