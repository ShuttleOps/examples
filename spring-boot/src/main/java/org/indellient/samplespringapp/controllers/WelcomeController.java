package org.indellient.samplespringapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController{
	
	@GetMapping("/")
	public String index() {
		return "index";
	}

}
