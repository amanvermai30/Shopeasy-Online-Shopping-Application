package com.shopeasy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopeasy.exception.CartException;
import com.shopeasy.exception.CustomerException;
import com.shopeasy.exception.PersonalInfoException;
import com.shopeasy.exception.ProductException;
import com.shopeasy.model.Cart;
import com.shopeasy.model.Customer;
import com.shopeasy.service.CustomerService;

@RestController
@RequestMapping("/customerController")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
	@PostMapping("/customer")
	public ResponseEntity<String> createCustomerAccount(@RequestBody Customer customer) throws CustomerException,PersonalInfoException{
		
		String outPut = customerService.createCustomerAccount(customer);
		return new ResponseEntity<String>(outPut,HttpStatus.CREATED);
	}

	@PostMapping("/addtocart/{productId}/{customerId}/{quantity}")
	public ResponseEntity<Cart>addProductToCartHandler(@PathVariable("productId") Integer productId,@PathVariable("customerId") Integer customerId,@PathVariable("quantity") Integer quantity ) throws CustomerException, ProductException, CartException{
		
		Cart updatedCart = customerService.addProductsToCart(productId,customerId, quantity);
		
		return new ResponseEntity<Cart>(updatedCart,HttpStatus.ACCEPTED);
	}
}
