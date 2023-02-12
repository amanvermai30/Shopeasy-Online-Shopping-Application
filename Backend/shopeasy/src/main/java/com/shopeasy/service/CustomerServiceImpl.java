package com.shopeasy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopeasy.exception.CustomerException;
import com.shopeasy.exception.PersonalInfoException;
import com.shopeasy.model.Customer;
import com.shopeasy.model.PersonalInfo;
import com.shopeasy.repository.CustomerDao;
import com.shopeasy.repository.PersonalInfoDao;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private PersonalInfoDao personalInfoDao;
	
	@Override
	public String createCustomerAccount(Customer customer) throws CustomerException,PersonalInfoException {
		// TODO Auto-generated method stub
		
		String output = "Customer account was not created.";

	    Customer savedCustomer = customerDao.save(customer);
	    if (savedCustomer != null) {
	        PersonalInfo personalInfo = customer.getPersonalInfo();
	        personalInfo.setCustomer(savedCustomer);
	        personalInfoDao.save(personalInfo);

	        output = "Customer account was created successfully.";
	    } else {
	        throw new CustomerException("Error while creating customer account.");
	    }

	    return output;

	}

}
