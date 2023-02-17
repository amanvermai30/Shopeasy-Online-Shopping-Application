package com.shopeasy.service;


import javax.security.auth.login.LoginException;

import com.shopeasy.exception.CartException;
import com.shopeasy.exception.CustomerException;
import com.shopeasy.exception.OrderClassException;
import com.shopeasy.exception.ProductException;
import com.shopeasy.model.OrderClass;

public interface OrderService {
	
	public OrderClass giveYourOrder(OrderClass order,Integer customerId)throws LoginException, CustomerException, CartException, ProductException,OrderClassException;

	public OrderClass cancelYourOrder(Integer orderId,String key)throws LoginException, CustomerException, CartException, ProductException,OrderClassException;
}
