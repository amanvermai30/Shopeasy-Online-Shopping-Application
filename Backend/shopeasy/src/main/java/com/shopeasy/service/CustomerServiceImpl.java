package com.shopeasy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopeasy.exception.CustomerException;
import com.shopeasy.exception.PersonalInfoException;
import com.shopeasy.model.Customer;
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
		
		String outPut = "Customer Account is Not created";
		
		Customer cust = customerDao.save(customer);
		if(cust != null ) {
			personalInfoDao.save(customer.getPersonalInfo());
			outPut = "Customer Account is created Successfully";
			
		}else {
			throw new PersonalInfoException("Error while creating customer account: ");
		}

		return outPut;
	}

}
