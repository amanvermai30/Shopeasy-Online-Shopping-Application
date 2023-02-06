package com.shopeasy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopeasy.exception.CustomerException;
import com.shopeasy.model.Customer;
import com.shopeasy.service.CustomerService;

@RestController
@RequestMapping("/customerController")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
	@PostMapping("/customer")
	public ResponseEntity<String> createCustomerAccount(@RequestBody Customer customer) throws CustomerException{
		
		String outPut = customerService.createCustomerAccount(customer);
		return new ResponseEntity<String>(outPut,HttpStatus.CREATED);
	}

}
