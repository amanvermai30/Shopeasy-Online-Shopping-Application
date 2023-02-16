package com.shopeasy.service;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.shopeasy.exception.CartException;
import com.shopeasy.exception.CustomerException;
import com.shopeasy.exception.OrderClassException;
import com.shopeasy.exception.ProductException;
import com.shopeasy.model.OrderClass;

public interface OrderService {
	
	public OrderClass giveYourOrder(OrderClass order,Integer customerId)throws LoginException, CustomerException, CartException, ProductException,OrderClassException;

	public OrderClass updateYourOrder(OrderClass order,String key)throws LoginException, CustomerException, CartException, ProductException;

	public OrderClass deleteYourOrder(OrderClass order,String key)throws LoginException, CustomerException, CartException, ProductException;

	public List<OrderClass> viewAllOrderByUserId(Integer userid) throws CustomerException;
}
