package com.ravali.spring.security.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ravali.spring.security.entity.User;
import com.ravali.spring.security.service.UserServie;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	private static final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);
	

	@Autowired
	UserServie userServie;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("username:{}",username);
		User user=userServie.findByUsername(username);
		logger.info("User:{}",user);
		if(user==null)
			throw new UsernameNotFoundException("User name or password wrong");
		MyUserDeatails myUserDeatails=new MyUserDeatails(user);
		return myUserDeatails;
	}

}
