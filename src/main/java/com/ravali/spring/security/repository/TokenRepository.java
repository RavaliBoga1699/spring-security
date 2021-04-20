package com.ravali.spring.security.repository;

import com.ravali.spring.security.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    Token findByToken(String token);
}
