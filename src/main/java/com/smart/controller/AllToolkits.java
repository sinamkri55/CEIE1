package com.smart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AllToolkits {

	@GetMapping("/toolkit1")
	public String toolkit1() {
		
		return "toolkit1";
	}
	
	
	@GetMapping("/toolkit2")
	public String toolkit2() {
		
		return "toolkit2";
	}
	
	
	
	@GetMapping("/toolkit3")
	public String toolkit3() {
		
		return "toolkit3";
	}
	
	
	
	@GetMapping("/toolkit4")
	public String toolkit4() {
		
		return "toolkit4";
	}
	
	
}
