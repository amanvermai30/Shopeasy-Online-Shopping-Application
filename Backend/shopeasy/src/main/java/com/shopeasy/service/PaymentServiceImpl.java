package com.shopeasy.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopeasy.enums.PaymentStatus;
import com.shopeasy.exception.CustomerException;
import com.shopeasy.exception.OrderClassException;
import com.shopeasy.exception.PaymentException;
import com.shopeasy.model.Cart;
import com.shopeasy.model.OrderClass;
import com.shopeasy.model.Payment;
import com.shopeasy.model.Product;
import com.shopeasy.repository.CartDao;
import com.shopeasy.repository.OrderClassDao;
import com.shopeasy.repository.PaymentDao;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	private OrderClassDao orderDao;
	
	@Autowired
	private PaymentDao paymentDao;
	
	@Autowired
	private CartDao cartDao; 
	
	@Override
	public Payment giveYourPayment(Payment payment, Integer orderId)
			throws PaymentException, CustomerException, OrderClassException {
		// TODO Auto-generated method stub
		
		Optional<OrderClass> orderOpt = orderDao.findById(orderId);
		OrderClass order = orderOpt.get();
		Payment outPut = null;
		
		if(order == null ) {
			throw new OrderClassException("Order not found ");
		}
		
		// I am taking this cart so that after payment I can empty customer cart -> there should not any product;
		Cart cart= order.getCustomer().getCart();
		
		payment.setPaymentCreatedAtDate(LocalDate.now());
		payment.setPaymentAmount(order.getTotalAmount());
		payment.setOrders(order);
		payment.setPaymentStatus(PaymentStatus.SUCCESSFULLY);
		orderDao.save(order);
		outPut  = paymentDao.save(payment);
		cartDao.delete(cart);
		
		return outPut;
	}

	@Override
	public Payment cancelPayment(Integer customerId, Integer paymentId) throws PaymentException, CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

}
