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

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private AdminServiceImpl aService;
	
	@GetMapping("/rest/{id}")
	@CircuitBreaker(name = "SpringProject2", fallbackMethod = "restGetByIdFallBack")
    public ResponseEntity<?> getEmployeeUsingRestTemplate(@Valid@PathVariable("id") int id) {
		log.info("Received request to get Employee using RestTemplate with id: {}", id);
        EmployeeBo emplBO = aService.getEmployeeUsingRestTemplet(id);
        return ResponseEntity.ok(emplBO);

	}
	
	//FallBack Method
	public ResponseEntity<?> restGetByIdFallBack(Throwable t){
		
		log.info("FallBack Method called for Getting Employee by ID using RestTemplete ");
		return ResponseEntity.status(500).body("SpringProject1 is not Running Properly");
	}
	
	
	@GetMapping("feignclient/{id}")
	@CircuitBreaker(name = "SpringProject2", fallbackMethod = "feignGetByIdFallBack")
	public ResponseEntity<EmployeeBo> getEmployeeUsingFeign(@Valid@PathVariable("id") int id){
		
		log.info("Received request to get item using FeignClient with id: {}", id);
		EmployeeBo eBo = aService.getEmployeeUsingFeign(id); 
		return ResponseEntity.ok(eBo);
	}
	
	//FallBack Method
	public ResponseEntity<?> feignGetByIdFallBack(Throwable t){
		
		log.info("FallBack Method called for Getting Employee by ID using Feign Client ");
		return ResponseEntity.status(500).body("SpringProject1 is not Running Properly");
	}
	
	
	@GetMapping("/feignclient")
	@CircuitBreaker(name = "SpringProject2", fallbackMethod = "feignGetAllFallBack")
	public ResponseEntity<?> getAllEmployeeFeign(){
		
		log.info("Received request to get all items using FeignClient");
		return ResponseEntity.ok(aService.getAllEmployeeUsinFeign());
	}
	
	//FallBack Method
	public ResponseEntity<?> feignGetAllFallBack(Throwable t){
		
		log.info("FallBack Method called for Getting All Employee using Feign Client ");
		return ResponseEntity.status(500).body("SpringProject1 is not Running Properly");
	}
	
	
	@GetMapping("/rest")
	@CircuitBreaker(name = "SpringProject2", fallbackMethod = "restGetAllFallBack")
	public ResponseEntity<?> getAllEmployeeRest(){
		
		log.info("Received request to get all items using RestTemplete");
		return ResponseEntity.ok(aService.getAllEmployeeUsingRest());
	}
	
	//FallBack Method
	public ResponseEntity<?> restGetAllFallBack(Throwable t){
		
		log.info("FallBack Method called for Getting All Employee using RestTemplete ");
		return ResponseEntity.status(500).body("SpringProject1 is not Running Properly");
	}
}
