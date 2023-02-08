package com.shopeasy.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopeasy.model.Admin;
import com.shopeasy.model.CurrentSession;
import com.shopeasy.model.Customer;
import com.shopeasy.model.Login;
import com.shopeasy.model.Vendor;
import com.shopeasy.repository.AdminDao;
import com.shopeasy.repository.CustomerDao;
import com.shopeasy.repository.PersonalInfoDao;
import com.shopeasy.repository.SessionDao;
import com.shopeasy.repository.VendorDao;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	PersonalInfoDao personalDao;
	
	@Autowired
	AdminDao adminDao;
	
	@Autowired
    SessionDao sessionDao;
	
	@Override
	public String loginUser(Login credential) throws LoginException {
		// TODO Auto-generated method stub
		
		CurrentSession currentSession;
		
		if( credential.getUser_type() != null && "CUSTOMER".equals(credential.getUser_type())) {
			
			Customer existingCustomer = personalDao.findByEmail(credential.getEmail());
			if(existingCustomer == null ) {
				
				throw new LoginException("Please Enter a valid CREDENTIALS");
				
			}else {
				
				
				Optional<CurrentSession> validCustomerSessionOpt = sessionDao.findById(existingCustomer.getCustomerId());
				if(validCustomerSessionOpt.isPresent()) {
					
					throw new LoginException("Customer already Logged In with this Email");
				}
				
				if(existingCustomer.getPersonalInfo().getPassword().equals(credential.getPassword())) {
					
					String key = RandomString.make(6);
					currentSession = new CurrentSession(existingCustomer.getCustomerId(),key,LocalDateTime.now(),credential.getUser_type());
					sessionDao.save(currentSession);
					return "Customer Logged in Successfully Welcome to shopeasy ";
					
				}else {
					throw new LoginException("Wrong Password");
				}
				
			}
			
			
		}else if(credential.getUser_type().equals("vendor")) {
			
			
			Vendor existingVendor = personalDao.findVendorByEmail(credential.getEmail());
			if(existingVendor == null ) {
				
				throw new LoginException("Please Enter a valid CREDENTIALS");
				
			}else {
				
				
				Optional<CurrentSession> validVendorSessionOpt = sessionDao.findById(existingVendor.getVendorId());
				if(validVendorSessionOpt.isPresent()) {
					
					throw new LoginException("Vendor already Logged In with this Email");
				}
				
				if(existingVendor.getPersonalInfo().getPassword().equals(credential.getPassword())) {
					
					String key = RandomString.make(6);
					currentSession = new CurrentSession(existingVendor.getVendorId(),key,LocalDateTime.now(),credential.getUser_type());
					sessionDao.save(currentSession);
					return "Vendor Logged in Successfully Welcome to shopeasy ";
					
				}else {
					throw new LoginException("Wrong Password");
				}
				
			}
			
			
		}else {
			
			
			Admin existingAdmin = adminDao.findByEmail(credential.getEmail());
			if(existingAdmin == null ) {
				
				throw new LoginException("Please Enter a valid CREDENTIALS");
				
			}else {
				
				
				Optional<CurrentSession> validAdminSessionOpt = sessionDao.findById(existingAdmin.getAdminId());
				if(validAdminSessionOpt.isPresent()) {
					
					throw new LoginException("Admin already Logged In with this Email");
				}
				
				if(existingAdmin.getPassword().equals(credential.getPassword())) {
					
					String key = RandomString.make(6);
					currentSession = new CurrentSession(existingAdmin.getAdminId(),key,LocalDateTime.now(),credential.getUser_type());
					sessionDao.save(currentSession);
					return "Hello Admin Welcome again to shopeasy ";
					
				}else {
					throw new LoginException("Wrong Password");
				}
				
			}
		}

	}

	@Override
	public String logoutUser(String key) throws LoginException {
		// TODO Auto-generated method stub
		
		CurrentSession validSession = sessionDao.findByUuid(key);
		
		if( validSession == null ) {
			throw new LoginException("Please Provide Valid Key");
		}
		
		sessionDao.delete(validSession);
		return "Logout Successfully";
	}

}
