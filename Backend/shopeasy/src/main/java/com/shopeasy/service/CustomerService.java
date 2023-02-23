package com.shopeasy.service;


import com.shopeasy.exception.CartException;
import com.shopeasy.exception.CustomerException;
import com.shopeasy.exception.PersonalInfoException;
import com.shopeasy.exception.ProductException;
import com.shopeasy.model.Cart;
import com.shopeasy.model.Customer;

public interface CustomerService {
	
	public String createCustomerAccount(Customer customer) throws CustomerException,PersonalInfoException;
	
	public Cart addProductsToCart(Integer productId,Integer customerId,Integer quantity) throws CartException,ProductException,CustomerException;
	
	public Customer updateCustomer(Customer customer,String key)throws CustomerException;
	
}
