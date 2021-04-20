package com.ravali.spring.security.controller;

import com.ravali.spring.security.models.User;
import com.ravali.spring.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/create")
    public User create(User user) {
        return userRepository.save(user);
    }
}
