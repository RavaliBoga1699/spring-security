package com.ravali.spring.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

	@NotBlank(message ="firstname is missing")
	private String firstName;

	@NotBlank(message = "lastname is missing")
	private String lastName;

	private String address;

	@NotBlank(message = "desination is missing")
	private String designation;

}
