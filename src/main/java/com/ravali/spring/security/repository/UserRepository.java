package com.ravali.spring.security.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ravali.spring.security.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	public User findByUsername(String username);
	

}
