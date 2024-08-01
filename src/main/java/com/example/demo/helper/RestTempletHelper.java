package com.example.demo.helper;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.data.EmployeeEo;
import com.example.demo.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RestTempletHelper {

	private final RestTemplate restTemplate;
	
	public RestTempletHelper() {
		this.restTemplate = new RestTemplate();
	}
	
	public EmployeeEo getEmployee(int id) {
		log.info("Calling first application using RestTemplet for id: {}",id);
		return restTemplate.getForObject(Constants.FIRST_APP_URL+"/api/employee/retrieval/"+id,EmployeeEo.class);
	}
	
	public List<EmployeeEo> getAllEmployee() {
		log.info("Calling first application  to get All Employee using RestTemplet for id");
		EmployeeEo[] emp = restTemplate.getForObject(Constants.FIRST_APP_URL+"/api/employee/all", EmployeeEo[].class);
		return Arrays.asList(emp);
	}
}
