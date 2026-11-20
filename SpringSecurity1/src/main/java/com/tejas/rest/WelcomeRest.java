package com.tejas.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeRest {

	@GetMapping("/welcome")
	public String hello() {
		return "Hello Spring Security";
	}
	
	@GetMapping("/user")
	public String user() {
		return "WELCOME USER";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "WELCOME ADMIN";
	}
	
	@GetMapping("/about")
	public String about() {
		return "ABOUT...";
	}
	
	@GetMapping("/contact")
	public String contact() {
		return "70########";
	}
}
