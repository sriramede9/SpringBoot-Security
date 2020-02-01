package com.sri.SpringSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/hi")
	public String test() {
		
		return ("<h2>Hi Sri!!<h2>");
	}

	@GetMapping("/user")
	public String testUser() {
		
		return ("<h2>Hi User!!<h2>");
	}
	@GetMapping("/admin")
	public String testAdmin() {
		
		return ("<h2>Hi Admin!!<h2>");
	}
	
	
}
