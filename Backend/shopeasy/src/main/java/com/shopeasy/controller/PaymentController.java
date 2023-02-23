package com.shopeasy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopeasy.exception.CustomerException;
import com.shopeasy.exception.OrderClassException;
import com.shopeasy.exception.PaymentException;
import com.shopeasy.model.Payment;
import com.shopeasy.service.PaymentService;

@RestController
@RequestMapping("/paymentController")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/")
	public String sayWelcome() {
		return "Welcome to shopeasy";
	}

	@PostMapping(value = "/giveyourpayment/{orderId}")
	public ResponseEntity<Payment> createPaymentHandler(@RequestBody Payment payment,
			@PathVariable("orderId") Integer orderId) throws PaymentException, CustomerException, OrderClassException{
		
		Payment outPut = paymentService.giveYourPayment(payment,orderId);
		return new ResponseEntity<Payment>(outPut,HttpStatus.CREATED);
		
	}

}
