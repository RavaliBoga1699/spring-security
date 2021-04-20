package com.ravali.spring.security.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ravali.spring.security.models.EmployeeEntity;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<EmployeeEntity, Long>{

}
