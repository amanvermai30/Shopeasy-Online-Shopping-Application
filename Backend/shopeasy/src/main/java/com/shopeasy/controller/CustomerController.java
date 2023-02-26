package com.shopeasy.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/")
	public String sayWelcome() {
		return "Welcome to shopeasy";
	}

	@PostMapping("/customer")
	public ResponseEntity<String> createCustomerAccount(@Valid @RequestBody Customer customer)
			throws CustomerException, PersonalInfoException {

		String outPut = customerService.createCustomerAccount(customer);
		return new ResponseEntity<String>(outPut, HttpStatus.CREATED);
	}

	@PostMapping("/addtocart/{productId}/{customerId}/{quantity}")
	public ResponseEntity<Cart> addProductToCartHandler(@PathVariable("productId") Integer productId,
			@PathVariable("customerId") Integer customerId, @PathVariable("quantity") Integer quantity)
			throws CustomerException, ProductException, CartException {

		Cart updatedCart = customerService.addProductsToCart(productId, customerId, quantity);

		return new ResponseEntity<Cart>(updatedCart, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteproductfromcart/{productId}/{customerId}/{key}")
	public ResponseEntity<Cart> deleteProductToCartHandler(@PathVariable("productId") Integer productId,
			@PathVariable("customerId") Integer customerId, @PathVariable("key") String key)
			throws CustomerException, ProductException, CartException {

		Cart updatedCart = customerService.deleteProductFromCart(productId, customerId, key);

		return new ResponseEntity<Cart>(updatedCart, HttpStatus.ACCEPTED);
	}

	@GetMapping("/getcartdetails/{customerId}")
	public ResponseEntity<Cart> getCartDetailsHandler(@PathVariable("customerId") Integer customerId)
			throws CustomerException, ProductException, CartException {

		Cart cart = customerService.getCartDetails(customerId);

		return new ResponseEntity<Cart>(cart, HttpStatus.ACCEPTED);
	}

}
