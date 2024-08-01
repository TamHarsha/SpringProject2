package com.example.demo.helper;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
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
	@Value("${emp.service.uri}")
	private String employeeUri;
	
	public EmployeeEo getEmployee(int id) {
		log.info("Calling first application using RestTemplet for id: {}",id);
		String url = employeeUri + "retrieval/{id}";
		return restTemplate.getForObject(url,EmployeeEo.class,id);
	}
	
	public List<EmployeeEo> getAllEmployee() {
		log.info("Calling first application  to get All Employee using RestTemplet for id");
		String url = employeeUri+"all";
		EmployeeEo[] emp = restTemplate.getForObject(url, EmployeeEo[].class);
		return Arrays.asList(emp);
	}
}
