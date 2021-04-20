package com.ravali.spring.security.controller;

import com.ravali.spring.security.dto.enums.TokenStatus;
import com.ravali.spring.security.models.Token;
import com.ravali.spring.security.models.Token.TokenBuilder;
import com.ravali.spring.security.repository.TokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ravali.spring.security.dto.JwtRequest;
import com.ravali.spring.security.dto.JwtResponse;
import com.ravali.spring.security.jwt.JwtUtil;

import java.util.Date;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenRepository tokenRepository;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtUtil.generateToken(userDetails);

        // Inserting token into database
        Token tokenModel = Token.builder()
                .createdDate(new Date())
                .token(token)
                .userName(userDetails.getUsername())
                .validUpto(2)
                .status(TokenStatus.ACTIVE)
                .build();
        tokenRepository.save(tokenModel);

        return ResponseEntity.ok(new JwtResponse(token));

    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }


}
