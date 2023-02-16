package com.shopeasy.controller;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopeasy.exception.CartException;
import com.shopeasy.exception.CustomerException;
import com.shopeasy.exception.OrderClassException;
import com.shopeasy.exception.ProductException;
import com.shopeasy.model.OrderClass;
import com.shopeasy.service.OrderService;

@RestController
@RequestMapping("/orderController")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PutMapping("/giveyourorder/{customerId}")
	public ResponseEntity<OrderClass> giveYourOrderHandler(@RequestBody OrderClass orders,@PathVariable("customerId") Integer customerId) throws LoginException, CustomerException, CartException, ProductException, OrderClassException{

		OrderClass outPut = orderService.giveYourOrder(orders, customerId);

		return new ResponseEntity<OrderClass>(outPut, HttpStatus.OK);

	}

}
