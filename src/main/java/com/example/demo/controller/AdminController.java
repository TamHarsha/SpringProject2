package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bo.EmployeeBo;
import com.example.demo.data.EmployeeEo;
import com.example.demo.service.AdminServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private AdminServiceImpl aService;
	
	@GetMapping("/rest/{id}")
    public ResponseEntity<EmployeeBo> getEmployeeUsingRestTemplate(@Valid@PathVariable("id") int id) {
		log.info("Received request to get item using RestTemplate with id: {}", id);
        EmployeeBo emplBO = aService.getEmployeeUsingRestTemplet(id);
        return ResponseEntity.ok(emplBO);

	}
	
	@GetMapping("feignclient/{id}")
	public ResponseEntity<EmployeeBo> getEmployeeUsingFeign(@Valid@PathVariable("id") int id){
		
		log.info("Received request to get item using FeignClient with id: {}", id);
		EmployeeBo eBo = aService.getEmployeeUsingFeign(id); 
		return ResponseEntity.ok(eBo);
	}
	
	@GetMapping("/feignclient")
	public ResponseEntity<?> getAllEmployeeFeign(){
		
		log.info("Received request to get all items using FeignClient");
		return ResponseEntity.ok(aService.getAllEmployeeUsinFeign());
	}
	
	@GetMapping("/rest")
	public ResponseEntity<?> getAllEmployeeRest(){
		
		log.info("Received request to get all items using FRestTemplete");
		return ResponseEntity.ok(aService.getAllEmployeeUsingRest());
	}
}
