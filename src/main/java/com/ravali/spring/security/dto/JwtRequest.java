package com.ravali.spring.security.dto;

import javax.validation.constraints.NotBlank;

public class JwtRequest {
	
	@NotBlank(message="username is missing")
	private String username;
	
	//@Password
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
