package com.ravali.spring.security.exeption.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ravali.spring.security.dto.ErrorResponseDTO;
import com.ravali.spring.security.exeption.UserNotLoggedInException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private ErrorResponseDTO errorResponseDTO;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ObjectError> errors = ex.getBindingResult().getAllErrors();
		List<String> errList=new ArrayList<>();
		for(ObjectError error:errors)
			errList.add(error.getDefaultMessage());
		errorResponseDTO.setErrors(errList);
		return new ResponseEntity<Object>(errorResponseDTO,status);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		List<String> errors=new ArrayList<>();
		errors.add("path paramter "+ex.getVariableName()+" is missing");
		errorResponseDTO.setErrors(errors);
		return new ResponseEntity<Object>(errorResponseDTO,status);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors=new ArrayList<>();
		errors.add("Request paramter "+ex.getParameterName()+" is missing");
		errorResponseDTO.setErrors(errors);
		return new ResponseEntity<Object>(errorResponseDTO,status);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
		List<String> errors=new ArrayList<>();
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		for(ConstraintViolation<?> violation:violations)
			errors.add(violation.getPropertyPath()+": "+violation.getMessage());
		errorResponseDTO.setErrors(errors);
		return new ResponseEntity<Object>(errorResponseDTO,HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors=new ArrayList<>();
		errors.add(ex.getMessage()+" Supported methods :"+ex.getSupportedHttpMethods());
		errorResponseDTO.setErrors(errors);
		return new ResponseEntity<Object>(errorResponseDTO,status);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	protected ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex) {
		List<String> errors=new ArrayList<>();
		errors.add(ex.getMessage());
		errorResponseDTO.setErrors(errors);
		return new ResponseEntity<Object>(errorResponseDTO,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotLoggedInException.class)
	protected ResponseEntity<Object> handleUserNotLoggedInException(UserNotLoggedInException ex) {
		List<String> errors=new ArrayList<>();
		errors.add("It seems you are not logged in to the server.");
		errorResponseDTO.setErrors(errors);
		return new ResponseEntity<Object>(errorResponseDTO,HttpStatus.BAD_REQUEST);
	}
	
	
	



}
