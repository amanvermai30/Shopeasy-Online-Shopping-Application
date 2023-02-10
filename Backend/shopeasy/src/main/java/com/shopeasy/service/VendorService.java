package com.shopeasy.service;

import com.shopeasy.exception.PersonalInfoException;
import com.shopeasy.exception.VendorException;
import com.shopeasy.model.Vendor;

public interface VendorService {
	
	public String createCustomerAccount(Vendor vendor) throws VendorException,PersonalInfoException;

}
