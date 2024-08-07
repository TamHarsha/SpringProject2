package com.example.demo.wiremock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.bo.EmployeeBo;
import com.example.demo.controller.AdminController;
import com.example.demo.data.EmployeeEo;
import com.example.demo.service.AdminServiceImpl;
import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {
	
	private WireMockServer wireMock;
	
	@InjectMocks
	private AdminController aController;
	
	@Mock
	private AdminServiceImpl aService;
	
//	 @BeforeEach
//	 void setup() {
//	     wireMock = new WireMockServer(8080);
//	     wireMock.start();
//	     stubFor(get(urlEqualTo("/employee/1"))
//	         .willReturn(aResponse()
//	             .withHeader("Content-Type", "application/json")
//	             .withBody("{ \"empId\": 1, \"employeeName\": \"Harsha\", \"employeeEmailId\": \"abc@gmail.com\", \"employeeAddress\": \"Hyd\", \"employeeNumber\": \"1234567890\" }")));
//	        
//	     stubFor(get(urlEqualTo("/employees"))
//	         .willReturn(aResponse()
//	             .withHeader("Content-Type", "application/json")
//	             .withBody("[{ \"empId\": 1, \"employeeName\": \"Harsha\", \"employeeEmailId\": \"abc@gmail.com\", \"employeeAddress\": \"Hyd\", \"employeeNumber\": \"1234567890\" }]")));
//	    }
//
//	 @AfterEach
//	 void teardown() {
//	        wireMock.stop();
//	    }

	@Test
	void testGetEmployeeUsingRestTemplate() {
		
		EmployeeBo eBo = new EmployeeBo(1,"Harsha","abc@gmail.com","Hyd","1234567890");
		//EmployeeBo eBo;
		when(aService.getEmployeeUsingRestTemplet(1)).thenReturn(eBo);
		
		ResponseEntity<?> response = aController.getEmployeeUsingRestTemplate(1);
		
		assertEquals(eBo, response.getBody());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//	    EmployeeBo responseBody = (EmployeeBo) response.getBody();
//	    assertThat(responseBody).isNotNull();
//	    assertThat(responseBody.getEmpId()).isEqualTo(1);
//	    assertThat(responseBody.getEmployeeName()).isEqualTo("Harsha");
//	    assertThat(responseBody.getEmployeeEmailId()).isEqualTo("abc@gmail.com");
//	    assertThat(responseBody.getEmployeeAddress()).isEqualTo("Hyd");
//	    assertThat(responseBody.getEmployeeNumber()).isEqualTo("1234567890");
		
	}

	@Test
	void testGetEmployeeUsingFeign() {
		
		EmployeeBo eBo = new EmployeeBo(1,"Harsha","abc@gmail.com","Hyd","1234567890");
		
		when(aService.getEmployeeUsingFeign(1)).thenReturn(eBo);
		
		ResponseEntity<?> response = aController.getEmployeeUsingFeign(1);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertEquals(eBo, response.getBody());
		
	}

	@Test
	void testGetAllEmployeeFeign() {
		
		EmployeeBo eBo = new EmployeeBo(1,"Harsha","abc@gmail.com","Hyd","1234567890");
		when(aService.getAllEmployeeUsinFeign()).thenReturn((Collections.singletonList(eBo)));
		
		ResponseEntity<?> response = aController.getAllEmployeeFeign();
		
		System.out.println("MY RESPONSE :"+response);
		System.out.println("MY RESPONSE :"+response.getBody());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		List<EmployeeBo> responseBody =  (List<EmployeeBo>) response.getBody();
		 
		assertEquals(1,responseBody.size());
		
	}

	@Test
	void testGetAllEmployeeRest() {
	
		EmployeeBo eBo = new EmployeeBo(1,"Harsha","abc@gmail.com","Hyd","1234567890");
		when(aService.getAllEmployeeUsingRest()).thenReturn(Collections.singletonList(eBo));
		
		ResponseEntity<?> response = aController.getAllEmployeeRest();
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		List<EmployeeBo> responseBody =  (List<EmployeeBo>) response.getBody();
		 
		assertEquals(1,responseBody.size());
		
	}

}
