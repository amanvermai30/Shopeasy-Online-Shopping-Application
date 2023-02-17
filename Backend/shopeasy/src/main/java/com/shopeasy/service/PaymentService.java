package com.shopeasy.service;

import com.shopeasy.exception.CustomerException;
import com.shopeasy.exception.OrderClassException;
import com.shopeasy.exception.PaymentException;
import com.shopeasy.model.Payment;

public interface PaymentService {


	public Payment giveYourPayment(Payment payment,Integer orderId) throws PaymentException,CustomerException,OrderClassException;
	
	public Payment cancelPayment(Integer customerId,Integer paymentId) throws PaymentException,CustomerException;
}
