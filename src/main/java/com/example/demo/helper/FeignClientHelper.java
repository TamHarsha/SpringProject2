package com.example.demo.helper;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.data.EmployeeEo;
import com.example.demo.util.Constants;

@Component
@FeignClient(name = "firstAppClient", url = "${emp.service.uri}")
public interface FeignClientHelper {
	
	@GetMapping("retrieval/{id}")
	EmployeeEo getEmployee(@PathVariable("id") int id);
	
	@GetMapping("/all")
	List<EmployeeEo> getAllEMployee();
	

}
