package com.ravali.spring.security.dto;

import java.util.List;

public class ErrorResponseDTO {
	List<String> errors;

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	
}
