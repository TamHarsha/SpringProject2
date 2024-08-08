package com.example.demo.bo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class EmployeeBo {
	
	@NotNull
	private int empId;
	
	@XmlElement
	@NotNull
	@Size(min = 3, max = 30, message = "Name should be between 3 to 30 letters ")
	private String employeeName;
	
	@XmlElement
	@NotNull
	@Email
	private String employeeEmailId;
	
	@XmlElement
	private String employeeAddress;
	
	@XmlElement
	private String employeeNumber;

}
