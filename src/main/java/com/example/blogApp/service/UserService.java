package com.example.blogApp.service;

import com.example.blogApp.dto.UserDTO;
import com.example.blogApp.models.User;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getOneUser(Integer id);
    String createUser(User user);
    String deleteUser(Integer id);
    User editUser(Integer id, User user);
}
