package com.shopeasy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopeasy.exception.PersonalInfoException;
import com.shopeasy.exception.VendorException;
import com.shopeasy.model.Vendor;
import com.shopeasy.service.VendorService;

@RestController
@RequestMapping("/vendorController")
public class VendorController {
	
	
	@Autowired
	private VendorService vendorService;
	
	
	@PostMapping("/vendor")
	public ResponseEntity<String> createCustomerAccount(@RequestBody Vendor vendor) throws VendorException,PersonalInfoException{
		
		String outPut = vendorService.createCustomerAccount(vendor);
		return new ResponseEntity<String>(outPut,HttpStatus.CREATED);
	}

}
