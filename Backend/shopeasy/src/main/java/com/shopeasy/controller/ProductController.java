package com.shopeasy.controller;

import java.util.List;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.shopeasy.enums.CategoryType;
import com.shopeasy.exception.CustomerException;
import com.shopeasy.exception.ProductException;
import com.shopeasy.model.Product;
import com.shopeasy.model.Vendor;
import com.shopeasy.service.ProductService;

@RestController
@RequestMapping("/productController")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/")
	public String sayWelcome() {
		return "Welcome to shopeasy";
	}

	@GetMapping("/viewallproducts")
	public ResponseEntity<List<Product>> viewAllProduct() throws ProductException, LoginException, CustomerException {

		List<Product> outPut = productService.viewAllProduct();
		return new ResponseEntity<List<Product>>(outPut, HttpStatus.CREATED);
	}

	@GetMapping("/singalproduct/{key}/{productId}")
	public ResponseEntity<Product> getSingalProduct(@PathVariable("key") String key,
			@PathVariable("productId") Integer productId) throws LoginException, ProductException, CustomerException {

		Product product = productService.getSingalProduct(key, productId);
		return new ResponseEntity<Product>(product, HttpStatus.ACCEPTED);

	}

	@GetMapping("/getproductbycategory/{category}/{key}")
	public ResponseEntity<List<Product>> getProductByCategory(@PathVariable("category") String category,
			@PathVariable("key") String key) throws LoginException, ProductException, CustomerException {

		try {
			
			// Converting the category string to the enum value
			CategoryType categoryType = CategoryType.valueOf(category.toUpperCase());

			List<Product> products =  productService.getProductByCategory(categoryType, key);
			
			return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
			
		} catch (IllegalArgumentException e) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid category");
		}

	}

}
