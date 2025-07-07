package com.example.blogApp.exception;

public class BlogAppException extends RuntimeException {
    public BlogAppException(String message) {
        super(message);
    }

    public BlogAppException(String message, Throwable cause) {
        super(message, cause);
    }
}
