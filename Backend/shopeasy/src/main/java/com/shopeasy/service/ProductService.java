package com.shopeasy.service;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.shopeasy.enums.CategoryType;
import com.shopeasy.exception.CustomerException;
import com.shopeasy.exception.ProductException;
import com.shopeasy.model.Product;

public interface ProductService {
	
	public Product getSingalProduct(String key,Integer productId)throws LoginException,ProductException;
	
	public List<Product> viewAllProduct() throws ProductException,LoginException,CustomerException;
	
	public List<Product> getProductByCategory(CategoryType category,String key) throws ProductException,LoginException,CustomerException; 

}
