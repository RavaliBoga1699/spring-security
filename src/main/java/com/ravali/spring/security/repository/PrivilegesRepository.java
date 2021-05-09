package com.ravali.spring.security.repository;

import com.ravali.spring.security.models.Privileges;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegesRepository extends JpaRepository<Privileges,Long> {
}
