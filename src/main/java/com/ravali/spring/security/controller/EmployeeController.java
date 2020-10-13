package com.ravali.spring.security.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ravali.spring.security.dto.EmployeeDTO;
import com.ravali.spring.security.entity.EmployeeEntity;
import com.ravali.spring.security.repository.EmployeeRepository;

@RestController
@Validated
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@GetMapping(value = "/getAllemployees", produces = "application/json")
	public ResponseEntity<Object> getAllEmployees(Authentication authentication) {
		Iterable<EmployeeEntity> employees = employeeRepository.findAll();
		logger.info("list of emplpoyees {}",employees);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllemployeesByPageAndSize", produces = "application/json")
	public ResponseEntity<Object> getAllEmployeesByPage(Authentication authentication,
			@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
		System.out.println(authentication.getAuthorities());
		Pageable pageable = PageRequest.of(page-1, size);
		Page<EmployeeEntity> employees = employeeRepository.findAll(pageable);
		return new ResponseEntity<>(employees.getContent(), HttpStatus.OK);
	}

	@PostMapping(value = "/create", produces = "application/json")
	public ResponseEntity<EmployeeDTO> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
		logger.info("::::::::::::::::::::::::validated");
		return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/getEmployee", produces = "application/json")
	public ResponseEntity<EmployeeDTO> getEmployee(@NotNull @RequestParam("id") Long id) {
		logger.info("::::::::::::::::::::::::validated");
		EmployeeDTO employeeDTO = new EmployeeDTO();
		return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/getEmployee/{id}", produces = "application/json")
	public ResponseEntity<EmployeeDTO> getEmployeeBypath(@NotNull @PathParam("id") Long id) {
		logger.info("::::::::::::::::::::::::validated");
		EmployeeDTO employeeDTO = new EmployeeDTO();
		return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/admin")
	public ResponseEntity<String> getAdmin() {
		String msg = "only admin has access";
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('USER')")
	@GetMapping(value = "/user")
	public ResponseEntity<String> getUser() {
		String msg = "only user has access";
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

}
