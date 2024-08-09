package com.example.demo.bo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeBo {
	
	@NotNull
	private int empId;
	
	@NotNull
	@Size(min = 3, max = 30, message = "Name should be between 3 to 30 letters ")
	private String employeeName;
	
	@NotNull
	@Email
	private String employeeEmailId;
	
	private String employeeAddress;
	
	private String employeeNumber;

}
