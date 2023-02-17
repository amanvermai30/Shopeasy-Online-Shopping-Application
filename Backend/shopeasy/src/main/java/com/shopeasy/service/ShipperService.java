package com.shopeasy.service;

import javax.security.auth.login.LoginException;

import com.shopeasy.exception.PaymentException;
import com.shopeasy.exception.ShipperException;
import com.shopeasy.exception.VendorException;
import com.shopeasy.model.OrderClass;
import com.shopeasy.model.Shipper;

public interface ShipperService {
	
	public OrderClass assignOrderToshipper(Shipper shipper, Integer orderId,Integer paymentId,String key) throws ShipperException,
	LoginException,VendorException,PaymentException;

}
