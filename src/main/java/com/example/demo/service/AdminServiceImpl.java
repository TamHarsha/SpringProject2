package com.example.demo.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Emapper.EMapper;
import com.example.demo.bo.EmployeeBo;
import com.example.demo.data.EmployeeEo;
import com.example.demo.helper.FeignClientHelper;
import com.example.demo.helper.RestTempletHelper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private RestTempletHelper restTempletHelper;
	
	@Autowired
	private FeignClientHelper feignClientHelper;
	
	@Autowired
	private EMapper eMapper;
	

	@Override
	public EmployeeBo getEmployeeUsingRestTemplet(int empId) {
		// TODO Auto-generated method stub
		log.info("Getting Employee details using RestTemplete form first app");
		EmployeeEo empl = restTempletHelper.getEmployee(empId);
		return eMapper.entityTOBo(empl);
	}

	@Override
	public EmployeeBo getEmployeeUsingFeign(int empId) {
		// TODO Auto-generated method stub
		log.info("Getting Employee details using FeignClient witj id: {}",empId);
		EmployeeEo empl = feignClientHelper.getEmployee(empId);
		return eMapper.entityTOBo(empl);
	}

	@Override
	public List<EmployeeBo> getAllEmployeeUsingRest() {
		// TODO Auto-generated method stub
		log.info("Getting all Employee using RestTemplate");
		return restTempletHelper.getAllEmployee().stream()
				.map(eMapper::entityTOBo)
				.collect(Collectors.toList());
	}

	@Override
	public List<EmployeeBo> getAllEmployeeUsinFeign() {
		// TODO Auto-generated method stub
		log.info("Getting all Employee using Feign Client");
		return feignClientHelper.getAllEMployee().stream()
				.map(eMapper::entityTOBo)
				.collect(Collectors.toList());
	}
	

	

}
