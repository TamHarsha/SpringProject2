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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.Emapper.EMapper;
import com.example.demo.bo.EmployeeBo;
import com.example.demo.data.EmployeeEo;
import com.example.demo.helper.FeignClientHelper;
import com.example.demo.helper.RestTempletHelper;
import com.example.demo.service.AdminServiceImpl;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

//@WireMockTest
@ExtendWith(MockitoExtension.class)
class AdminServiceTest {
	
	//private WireMockServer wireMockServer;
	
	@InjectMocks
	private AdminServiceImpl aService;
	
	@Mock
	private EMapper eMapper;
	
	@Mock
	private RestTempletHelper restTempletHelper;
	
	@Mock
	private FeignClientHelper feignClientHelper;
	

	
//	@BeforeEach
//	void setUp() {
//		wireMockServer = new WireMockServer(8080);
//		wireMockServer.start();
//		
//		 stubFor(get(urlEqualTo("/employee/1"))
//				 .willReturn(aResponse()
//	                .withHeader("Content-Type", "application/json")
//	                .withBody("{ \"id\": 1, \"name\": \"Harsha\",\"email\": \"abc@gmail.com\", \"address\": \"Hyd\", \"phone\": \"1234567890\" }")));
//	}
//	
//	@AfterEach
//	void teardown() {
//        wireMockServer.stop();
//    }

	@Test
	void testGetEmployeeUsingRestTemplet() {
		EmployeeEo eEo = new EmployeeEo(1,"Harsha","abc@gmail.com","Hyd","0123456789");
		EmployeeBo eBo = new EmployeeBo(1,"Harsha","abc@gmail.com","Hyd","0123456789");
		
		when(restTempletHelper.getEmployee(1)).thenReturn(eEo);
		when(eMapper.entityTOBo(eEo)).thenReturn(eBo);
		
		EmployeeBo result = aService.getEmployeeUsingRestTemplet(1);
		
		assertEquals(eBo, result);

	}

	@Test
	void testGetEmployeeUsingFeign() {
		
		EmployeeEo eEo = new EmployeeEo(1,"Harsha","abc@gmail.com","Hyd","0123456789");
		EmployeeBo eBo = new EmployeeBo(1,"Harsha","abc@gmail.com","Hyd","0123456789");
		
		when(feignClientHelper.getEmployee(1)).thenReturn(eEo);
		when(eMapper.entityTOBo(eEo)).thenReturn(eBo);
		
		EmployeeBo result = aService.getEmployeeUsingFeign(1);
		
		assertEquals(eBo, result);
		
	}

	@Test
	void testGetAllEmployeeUsingRest() {
		
		EmployeeEo eEo = new EmployeeEo(1,"Harsha","abc@gmail.com","Hyd","0123456789");
		EmployeeBo eBo = new EmployeeBo(1,"Harsha","abc@gmail.com","Hyd","0123456789");

		when(restTempletHelper.getAllEmployee()).thenReturn(Collections.singletonList(eEo));
		when(eMapper.entityTOBo(eEo)).thenReturn(eBo);
		
		List<EmployeeBo> result = aService.getAllEmployeeUsingRest();
		
		assertEquals(1, result.size());

	}

	@Test
	void testGetAllEmployeeUsinFeign() {
		
		EmployeeEo eEo = new EmployeeEo(1,"Harsha","abc@gmail.com","Hyd","0123456789");
		EmployeeBo eBo = new EmployeeBo(1,"Harsha","abc@gmail.com","Hyd","0123456789");
		
		when(feignClientHelper.getAllEMployee()).thenReturn(Collections.singletonList(eEo));
		when(eMapper.entityTOBo(eEo)).thenReturn(eBo);
		
		List<EmployeeBo> result = aService.getAllEmployeeUsinFeign();
		
		assertEquals(1, result.size());
	}

}
