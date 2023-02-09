package com.shopeasy.service;

import com.shopeasy.exception.PersonalInfoException;
import com.shopeasy.exception.VendorException;

public interface VendorService {
	
	public String createCustomerAccount(VendorService vendorService) throws VendorException,PersonalInfoException;

}
