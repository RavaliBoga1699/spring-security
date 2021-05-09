package com.ravali.spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import com.ravali.spring.security.exeption.UserNotLoggedInException;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@Validated
public class LoginController {

    @GetMapping(value = "/login", produces = "application/json")
    public ResponseEntity<Object> getlogin(@Autowired Authentication authentication) {

        if (authentication == null)
            throw new UserNotLoggedInException();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        HashMap<String, Object> data = new HashMap<>();
        data.put("msg", "you are already logged in");
        data.put("payload", userDetails);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping(value = "/loginSuccess", produces = "application/json")
    public ResponseEntity<Object> getloginSuccess() {
        return new ResponseEntity<>("you are successfully logged in", HttpStatus.OK);
    }

    @GetMapping(value = "/logoutSuccess", produces = "application/json")
    public ResponseEntity<Object> logoutSuccess() {
        return new ResponseEntity<>("you are successfully logged out", HttpStatus.OK);
    }

}
