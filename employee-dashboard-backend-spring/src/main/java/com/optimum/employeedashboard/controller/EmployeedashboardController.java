package com.optimum.employeedashboard.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @CrossOrigin(origins = {"http://localhost:8090", "http://192.168.200.18","http://192.168.200.14"})
// Annotation replaced by CorsConfiguration class
public class EmployeedashboardController {
	// 192.168.200.18 == Razli's computer
	// 192.168.200.14 == Adam's laptop
	// Test
	@GetMapping("/")
	public String home() {
		return "Hello World";
	}

  
}
