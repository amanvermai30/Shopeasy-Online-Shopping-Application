package com.shopeasy.service;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.shopeasy.exception.AdminException;
import com.shopeasy.exception.CustomerException;
import com.shopeasy.exception.VendorException;
import com.shopeasy.model.Customer;
import com.shopeasy.model.Vendor;

public interface AdminService {
	
	public List<Customer> viewAllCustomer(String key) throws CustomerException,LoginException,AdminException;
	
	public List<Vendor> viewAllVendor(String key) throws VendorException,LoginException,AdminException;
	
}
