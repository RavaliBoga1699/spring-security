package com.ravali.spring.security.repository;

import org.springframework.data.repository.CrudRepository;

import com.ravali.spring.security.entity.EmployeeEntity;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long>{

}
