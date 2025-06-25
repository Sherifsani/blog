package com.example.blogApp.controller;

import com.example.blogApp.models.User;
import com.example.blogApp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/get/{id}")
    public User getOneUser(@PathVariable Integer id){
        return userService.getOneUser(id);
    }

    @PostMapping("/create")
    public String createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id){
        return userService.deleteUser(id);
    }

    @PutMapping("/edit")
    public User editUser(@RequestBody Integer id, String name, String bio){
        return userService.editUser(id, name, bio);
    }
}
