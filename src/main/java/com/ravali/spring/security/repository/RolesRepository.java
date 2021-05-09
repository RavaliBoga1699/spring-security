package com.ravali.spring.security.repository;

import com.ravali.spring.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolesRepository extends JpaRepository<Role,Long> {
    List<Role> getById(Long id);
}
