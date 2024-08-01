package com.example.demo.util;

import org.springframework.beans.factory.annotation.Value;

public class Constants {
	
	@Value("${emp.service.uri}")
	public static   String FIRST_APP_URL;
	
	//public static final String FIRST_APP_URL = "http://localhost:8081//api/employee/";

}
