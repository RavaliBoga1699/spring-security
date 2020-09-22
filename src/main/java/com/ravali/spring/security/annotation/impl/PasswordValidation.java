package com.ravali.spring.security.annotation.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ravali.spring.security.annotation.Password;

public class PasswordValidation implements 
ConstraintValidator<Password, String> {

	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
	@Override
	public void initialize( Password password) {
		System.out.println("initialized");
	}
	
	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		Pattern pattern=Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher=pattern.matcher(password);
		return matcher.matches();
	}
	
}