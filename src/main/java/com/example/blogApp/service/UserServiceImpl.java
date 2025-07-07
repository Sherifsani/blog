package com.example.blogApp.service;

import com.example.blogApp.dto.UserDTO;
import com.example.blogApp.exception.UserNotFoundException;
import com.example.blogApp.models.User;
import com.example.blogApp.repository.UserRepository;
import com.example.blogApp.util.UserMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getOneUser(Integer id) {
        User targetUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return UserMapper.toDTO(targetUser);
    }

    @Override
    public String createUser(User user) {
        userRepository.save(user);
        return "user created successfully";
    }

    @Override
    public String deleteUser(Integer id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "User deleted successfully";
    }

    @Override
    public User editUser(Integer id, User user) {
        User targetUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        targetUser.setBio(user.getBio());
        targetUser.setUsername(user.getUsername());
        userRepository.save(targetUser);
        return targetUser;
    }

    @Override
    public User findUserByName(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + username));
    }
}
