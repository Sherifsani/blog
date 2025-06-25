package com.example.blogApp.service;

import com.example.blogApp.models.User;
import com.example.blogApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @Override
    public User getOneUser(Integer id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public String createUser(User  user){
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "user created successfully";
    }

    @Override
    public String deleteUser(Integer id){
        try{
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "User deleted successfully";
    }

    @Override
    public User editUser(Integer id, String name, String bio){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        user.setBio(bio);
        user.setUsername(name);
        userRepository.save(user);
        return user;
    }
}
