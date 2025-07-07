package com.example.blogApp.controller;

import com.example.blogApp.dto.UserDTO;
import com.example.blogApp.models.User;
import com.example.blogApp.service.UserService;
import com.example.blogApp.util.UserMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getOneUser(@PathVariable Integer id) {
        return userService.getOneUser(id);
    }

    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        String message = userService.createUser(user);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/edit/{id}")
    public User editUser(
            @PathVariable Integer id,
            @RequestBody User user) {
        return userService.editUser(id, user);
    }
}
