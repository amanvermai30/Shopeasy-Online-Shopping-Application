package com.shopeasy.service;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.shopeasy.exception.CartException;
import com.shopeasy.exception.CustomerException;
import com.shopeasy.exception.PersonalInfoException;
import com.shopeasy.exception.ProductException;
import com.shopeasy.model.Cart;
import com.shopeasy.model.Customer;
import com.shopeasy.model.Product;

public interface CustomerService {
	
	public String createCustomerAccount(Customer customer) throws CustomerException,PersonalInfoException;
	
	public List<Product> viewAllProduct(String key) throws ProductException, LoginException,CustomerException;
	
	public Cart addProductsToCart(Integer productId,Integer customerId,Integer quantity) throws CartException,ProductException,CustomerException;
	
}
