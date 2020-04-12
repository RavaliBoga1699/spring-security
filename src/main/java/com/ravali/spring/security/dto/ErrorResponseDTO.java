package com.ravali.spring.security.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ErrorResponseDTO {
	List<String> errors;

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	
}
