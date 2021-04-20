package com.ravali.spring.security.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravali.spring.security.models.User;
import com.ravali.spring.security.repository.UserRepository;

@Service
public class UserServie {
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> findAll(){
		Iterable<User> users = userRepository.findAll();
		Iterator<User> iterator=users.iterator();
		List<User> userList=new ArrayList<>();
		 while (iterator.hasNext()) {
		        userList.add(iterator.next());
		 }
		 return userList;
	}
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
