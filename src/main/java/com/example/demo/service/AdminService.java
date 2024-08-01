package com.example.demo.service;

import java.util.List;

import com.example.demo.bo.EmployeeBo;
import com.example.demo.data.EmployeeEo;

public interface AdminService {
	
	List<EmployeeBo> getAllEmployeeUsingRest();
	List<EmployeeBo> getAllEmployeeUsinFeign();
	EmployeeBo getEmployeeUsingRestTemplet(int empId);
	EmployeeBo getEmployeeUsingFeign(int empId);

}
