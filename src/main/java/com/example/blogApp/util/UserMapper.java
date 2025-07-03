package com.example.blogApp.util;

import com.example.blogApp.models.User;
import com.example.blogApp.dto.UserDTO;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        return new UserDTO(user.getUsername(), user.getBio());
    }

    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setBio(userDTO.getBio());
        return user;
    }
}
