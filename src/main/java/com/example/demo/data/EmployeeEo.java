package com.example.demo.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEo {
	
	private int empId;
	private String employeeName;
	private String employeeEmailId;
	private String employeeAddress;
	private String employeeNumber;

}
