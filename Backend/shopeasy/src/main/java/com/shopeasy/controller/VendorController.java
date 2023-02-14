package com.shopeasy.controller;

import java.util.List;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopeasy.exception.PersonalInfoException;
import com.shopeasy.exception.ProductException;
import com.shopeasy.exception.VendorException;
import com.shopeasy.model.Product;
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
	
	@PostMapping("/addproduct/{key}")
	public ResponseEntity<String> addProduct(@RequestBody Product product,@PathVariable("key") String key )throws ProductException, LoginException, VendorException{
		
		String outPut = vendorService.addProduct(product,key);
		return new ResponseEntity<String>(outPut,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteproduct/{productId}/{key}")
	public ResponseEntity<String> removeProduct(@PathVariable("productId") Integer productId,@PathVariable("key") String key )throws ProductException, LoginException, VendorException{
		
		String outPut = vendorService.removeProduct(productId,key);
		return new ResponseEntity<String>(outPut,HttpStatus.OK);
	}
	
	@PutMapping("/updateproduct/{key}")
	public ResponseEntity<String> updateProduct(@RequestBody Product product,@PathVariable("key") String key )throws ProductException, LoginException, VendorException{
		
		String outPut = vendorService.updateProduct(product,key);
		return new ResponseEntity<String>(outPut,HttpStatus.CREATED);
	}
	
	@GetMapping("/viewallproducts")
	public ResponseEntity<List<Product>> viewAllProduct()throws ProductException, LoginException, VendorException{
		
		List<Product> outPut= vendorService.viewAllProduct();
		return new ResponseEntity<List<Product>>(outPut,HttpStatus.CREATED);
	}
	
	@GetMapping("/viewproduct/{productId}")
	public ResponseEntity<Product> viewProduct(@PathVariable("productId") Integer productId)throws ProductException, LoginException, VendorException{
		
		Product outPut= vendorService.viewProductById(productId);
		return new ResponseEntity<Product>(outPut,HttpStatus.CREATED);
	}

}
