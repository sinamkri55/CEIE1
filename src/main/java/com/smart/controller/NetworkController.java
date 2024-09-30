package com.smart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NetworkController {

	
	@GetMapping("/Al_Akhawayn_University")
	public String Al_Akhawayn_University() {
		
		return "Al_Akhawayn_University";
		


	}
	
	@GetMapping("/USMBA")
	public String USMBA() {
		
		return "USMBA";
		


	}

}
