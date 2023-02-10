package com.shopeasy.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.shopeasy.exception.PersonalInfoException;
import com.shopeasy.exception.VendorException;
import com.shopeasy.model.Customer;
import com.shopeasy.model.Vendor;
import com.shopeasy.repository.PersonalInfoDao;
import com.shopeasy.repository.VendorDao;

public class VendorServiceImpl implements VendorService{
	
	@Autowired
	VendorDao vendorDao;
	
	@Autowired
	PersonalInfoDao personalInfoDao;

	@Override
	public String createCustomerAccount(Vendor vendor) throws VendorException, PersonalInfoException {
		// TODO Auto-generated method stub
		
        String outPut = "Customer Account is Not created";
		
		Vendor ven = vendorDao.save(vendor);
		if(ven != null ) {
			personalInfoDao.save(vendor.getPersonalInfo());
			outPut = "Vendor Account is created Successfully";
			
		}else {
			throw new PersonalInfoException("Error while creating vendor account: ");
		}

		return outPut;
	}

}
