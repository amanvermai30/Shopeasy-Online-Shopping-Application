package com.shopeasy.service;


import java.io.IOException;
import java.util.List;

import javax.security.auth.login.LoginException;

import org.springframework.web.multipart.MultipartFile;

import com.shopeasy.exception.PersonalInfoException;
import com.shopeasy.exception.ProductException;
import com.shopeasy.exception.VendorException;
import com.shopeasy.model.Product;
import com.shopeasy.model.Vendor;

public interface VendorService {
	
	public String createVendorAccount(Vendor vendor) throws VendorException,PersonalInfoException,IOException;
	
	public String addProduct(Product product,String key) throws ProductException,LoginException,VendorException;
	
	public String updateProduct(Product product, String key) throws ProductException,LoginException,VendorException;
	
	public List<Product> viewAllProduct() throws ProductException,LoginException,VendorException;
	
	public String removeProduct(Integer productId,String key) throws ProductException,LoginException,VendorException;
	
	public Product viewProductById(Integer productId) throws ProductException,LoginException,VendorException;
	
}
