package com.shopeasy.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;


public class GlobalExceptionHandler {

//	Exception Handler for Exception class
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> exceptionClassHandler(Exception exception,WebRequest request){
		
		
		ErrorDetails details=new ErrorDetails(LocalDateTime.now(),exception.getMessage(),request.getDescription(false));
		ResponseEntity<ErrorDetails> entity=new ResponseEntity<ErrorDetails>(details,HttpStatus.BAD_REQUEST); 
	   
		return entity;
	}
	
	

//	Data validation exception handler
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> exceptionClassHandler(MethodArgumentNotValidException exception){
		
		
		ErrorDetails details = new ErrorDetails(LocalDateTime.now(),exception.getMessage());
		details.setDetails(exception.getBindingResult().getFieldError().getDefaultMessage());
		ResponseEntity<ErrorDetails> entity=new ResponseEntity<ErrorDetails>(details,HttpStatus.BAD_REQUEST); 
	   
		return entity;
	}
	
	
	
//	Exception Handler for No URI found or wrong uri
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetails> exceptionClassHandler(NoHandlerFoundException exception,WebRequest request){
		
		
		ErrorDetails details=new ErrorDetails(LocalDateTime.now(),exception.getMessage(),request.getDescription(false));
		ResponseEntity<ErrorDetails> entity=new ResponseEntity<ErrorDetails>(details,HttpStatus.BAD_REQUEST); 
	   
		return entity;
	}
	
//	Exception handler for CustomerException
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<ErrorDetails> CustomerExceptionClassHandler(CustomerException exception,WebRequest request){
		
		
		ErrorDetails details=new ErrorDetails(LocalDateTime.now(),exception.getMessage(),request.getDescription(false));
		ResponseEntity<ErrorDetails> entity=new ResponseEntity<ErrorDetails>(details,HttpStatus.BAD_REQUEST); 
	   
		return entity;
	}
		
//	Exception handler for ProductException
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<ErrorDetails> ProductExceptionClassHandler(ProductException exception,WebRequest request){
		
		
		ErrorDetails details=new ErrorDetails(LocalDateTime.now(),exception.getMessage(),request.getDescription(false));
		ResponseEntity<ErrorDetails> entity=new ResponseEntity<ErrorDetails>(details,HttpStatus.BAD_REQUEST); 
	   
		return entity;
	}	

//	Exception handler for ShipperException
	@ExceptionHandler(ShipperException.class)
	public ResponseEntity<ErrorDetails> ShipperExceptionClassHandler(ShipperException exception,WebRequest request){
		
		
		ErrorDetails details=new ErrorDetails(LocalDateTime.now(),exception.getMessage(),request.getDescription(false));
		ResponseEntity<ErrorDetails> entity=new ResponseEntity<ErrorDetails>(details,HttpStatus.BAD_REQUEST); 
	   
		return entity;
	}
	
//	Exception handler for VendorException
	@ExceptionHandler(VendorException.class)
	public ResponseEntity<ErrorDetails> VendorExceptionClassHandler(VendorException exception,WebRequest request){
		
		
		ErrorDetails details=new ErrorDetails(LocalDateTime.now(),exception.getMessage(),request.getDescription(false));
		ResponseEntity<ErrorDetails> entity=new ResponseEntity<ErrorDetails>(details,HttpStatus.BAD_REQUEST); 
	   
		return entity;
	}	
	

//	Exception handler for CartException
	@ExceptionHandler(CartException.class)
	public ResponseEntity<ErrorDetails> CartExceptionClassHandler(CartException exception,WebRequest request){
		
		
		ErrorDetails details=new ErrorDetails(LocalDateTime.now(),exception.getMessage(),request.getDescription(false));
		ResponseEntity<ErrorDetails> entity=new ResponseEntity<ErrorDetails>(details,HttpStatus.BAD_REQUEST); 
	   
		return entity;
	}	
	
	
}
