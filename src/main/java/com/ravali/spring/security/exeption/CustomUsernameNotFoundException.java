package com.ravali.spring.security.exeption;

public class CustomUsernameNotFoundException extends RuntimeException{

	public CustomUsernameNotFoundException(String msg) {
		super(msg);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}

