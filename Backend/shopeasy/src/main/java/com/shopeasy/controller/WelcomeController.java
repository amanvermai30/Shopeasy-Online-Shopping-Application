package com.shopeasy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	
	@GetMapping("/")
	public String sayWelcome() {
		return "Welcome to shopeasy visit notion for more details about APIs : https://unequaled-pulsar-4bd.notion.site/Shopeasy-com-43bb81d8e3034d87ad4ccdb6ea996c82";
	}

}
