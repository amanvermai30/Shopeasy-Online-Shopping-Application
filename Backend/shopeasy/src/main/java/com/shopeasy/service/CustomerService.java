package com.shopeasy.service;

import com.shopeasy.exception.CustomerException;
import com.shopeasy.model.Customer;

public interface CustomerService {
	
	public String createCustomerAccount(Customer customer) throws CustomerException;

}
