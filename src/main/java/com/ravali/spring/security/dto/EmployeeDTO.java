package com.ravali.spring.security.dto;

import javax.validation.constraints.NotBlank;

public class EmployeeDTO {

	@NotBlank(message ="firstname is missing")
	private String firstName;

	@NotBlank(message = "lastname is missing")
	private String lastName;

	private String address;

	@NotBlank(message = "desination is missing")
	private String designation;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}





}
