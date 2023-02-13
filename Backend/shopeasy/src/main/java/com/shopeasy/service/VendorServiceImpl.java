package com.shopeasy.service;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopeasy.exception.PersonalInfoException;
import com.shopeasy.exception.ProductException;
import com.shopeasy.exception.VendorException;
import com.shopeasy.model.CurrentSession;
import com.shopeasy.model.PersonalInfo;
import com.shopeasy.model.Product;
import com.shopeasy.model.UserType;
import com.shopeasy.model.Vendor;
import com.shopeasy.repository.PersonalInfoDao;
import com.shopeasy.repository.ProductDao;
import com.shopeasy.repository.SessionDao;
import com.shopeasy.repository.VendorDao;

@Service
public class VendorServiceImpl implements VendorService{
	
	@Autowired
	VendorDao vendorDao;
	
	@Autowired
	PersonalInfoDao personalInfoDao;
	
	@Autowired
	SessionDao sessionDao;
	
	@Autowired
	ProductDao productDao;

	@Override
	public String createCustomerAccount(Vendor vendor) throws VendorException, PersonalInfoException {
		// TODO Auto-generated method stub
		
        String outPut = "Customer Account is Not created";
		
		Vendor ven = vendorDao.save(vendor);
		if(ven != null ) {
			
			PersonalInfo personalInfo = ven.getPersonalInfo();
			personalInfo.setVendor(ven);
			personalInfoDao.save(personalInfo);
			outPut = "Vendor Account is created Successfully";
			
		}else {
			throw new PersonalInfoException("Error while creating vendor account: ");
		}

		return outPut;
	}

	@Override
	public String addProduct(Product product, String key) throws ProductException, LoginException, VendorException {
		// TODO Auto-generated method stub
		
		String outPut = "Product is Not added";
		CurrentSession session = sessionDao.findByUuid(key);
		if(session == null ) {
			throw new LoginException("Vendor Not logged in ");
			
		}else {
			
			if(session.getUser_type().equals(UserType.CUSTOMER) || session.getUser_type().equals(UserType.ADMIN)){
			    throw new LoginException("you are not authorized");	
			    
			}else {
				
				productDao.save(product);
				outPut = "Product is added";
			}
		}
		return outPut;
	}

	@Override
	public String updateProduct(Product product, String key) throws ProductException, LoginException, VendorException {
		// TODO Auto-generated method stub
		
		String outPut = "Product is Not added";
		CurrentSession session = sessionDao.findByUuid(key);
		if(session == null ) {
			throw new LoginException("Vendor Not logged in ");
			
		}else {
			
			if(session.getUser_type().equals(UserType.CUSTOMER) || session.getUser_type().equals(UserType.ADMIN)){
			    throw new LoginException("you are not authorized");	
			    
			}else {
				
				Optional<Product> pro = productDao.findById(product.getProductId());
				
				if(pro.isPresent()) {
					productDao.save(product);
					outPut = "Product is updated successfully";
					
				}else {
					throw new ProductException("Product not found with this Id"+product.getProductId());
				}
				
				
			}
		}
		return outPut;
	}

	@Override
	public List<Product> viewAllProduct() throws ProductException, LoginException, VendorException {
		// TODO Auto-generated method stub
		
		List<Product> products = productDao.findAll();
		
		if(products.isEmpty()) {
			throw new ProductException("Product is currently not available ");
		}
		return products;
		
	}

	@Override
	public String removeProduct(Integer productId, String key)
			throws ProductException, LoginException, VendorException {
		// TODO Auto-generated method stub
		
		String outPut = "Product is Not deleted";
		CurrentSession session = sessionDao.findByUuid(key);
		if(session == null ) {
			throw new LoginException("Vendor Not logged in ");
			
		}else {
			
			if(session.getUser_type().equals(UserType.CUSTOMER) || session.getUser_type().equals(UserType.ADMIN)){
			    throw new LoginException("you are not authorized");	
			    
			}else {
				
				Optional<Product> pro = productDao.findById(productId);
				
				if(pro.isPresent()) {
					productDao.delete(pro.get());
					outPut = "Product is deleted successfully";
					
				}else {
					throw new ProductException("Product not found with this Id"+productId);
				}
				
				
			}
		}
		return outPut;
	}

	@Override
	public Product viewProductById(Integer productId)
			throws ProductException, LoginException, VendorException {
		// TODO Auto-generated method stub
		
		return productDao.findById(productId).orElseThrow(()-> new ProductException("Product not found with this Id"+productId));
	}

}
