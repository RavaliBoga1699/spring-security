package com.ravali.spring.security.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ravali.spring.security.dto.EmployeeDTO;

@RestController
@Validated
public class EmployeeController {
	
	
	
	 private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@GetMapping(value = "/getAllemployees", produces = "application/json")
	public String getAllEmployees(Authentication authentication) {
		System.out.println(authentication.getAuthorities());
		return "{\"employee\":{\r\n" + 
				"\"name\":\"ravali\"\r\n" + 
				"}}";
	}
	
	@PostMapping(value = "/create",produces = "application/json")
	public ResponseEntity<EmployeeDTO> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){
		logger.info("::::::::::::::::::::::::validated");
		return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getEmployee", produces = "application/json")
	public ResponseEntity<EmployeeDTO> getEmployee(@NotNull @RequestParam("id") Long id){
		logger.info("::::::::::::::::::::::::validated");
		EmployeeDTO employeeDTO=new EmployeeDTO();
		return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getEmployee/{id}", produces = "application/json")
	public ResponseEntity<EmployeeDTO> getEmployeeBypath(@NotNull @PathParam("id") Long id){
		logger.info("::::::::::::::::::::::::validated");
		EmployeeDTO employeeDTO=new EmployeeDTO();
		return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/admin")
	public ResponseEntity<String> getAdmin(){
		String msg="only admin has access";
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('USER')")
	@GetMapping(value = "/user")
	public ResponseEntity<String> getUser(){
		String msg="only user has access";
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	

}
