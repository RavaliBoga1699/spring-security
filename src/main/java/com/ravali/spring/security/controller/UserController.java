package com.ravali.spring.security.controller;

import com.ravali.spring.security.dto.UserDto;
import com.ravali.spring.security.models.Role;
import com.ravali.spring.security.models.User;
import com.ravali.spring.security.repository.RolesRepository;
import com.ravali.spring.security.repository.UserRepository;
import com.ravali.spring.security.service.DbSeederService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    DbSeederService dbSeederService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/add")
    public User create(@RequestBody UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        List<Role> roles = rolesRepository.getById(userDto.getRole_id());
        user.setRoles(roles);


        user = userRepository.save(user);
        return user;
    }

    @GetMapping("/getAll")
    public List<User> getUsers(){
        Iterable<User> userIterator = userRepository.findAll();
        List<User> userList = new ArrayList<>();
        userIterator.forEach(userList::add);
        return userList;
    }



    @GetMapping("/seedData")
    public String seedData() {
        dbSeederService.addDefaultRoles();
        return "success";
    }
}
