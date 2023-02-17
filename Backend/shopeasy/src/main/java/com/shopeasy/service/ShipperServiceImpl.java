package com.shopeasy.service;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopeasy.enums.PaymentStatus;
import com.shopeasy.exception.PaymentException;
import com.shopeasy.exception.ShipperException;
import com.shopeasy.exception.VendorException;
import com.shopeasy.model.OrderClass;
import com.shopeasy.model.Payment;
import com.shopeasy.model.Shipper;
import com.shopeasy.repository.OrderClassDao;
import com.shopeasy.repository.PaymentDao;
import com.shopeasy.repository.ShipperDao;

@Service
public class ShipperServiceImpl implements ShipperService{

	@Autowired
	private OrderClassDao orderDao;
	
	@Autowired
	private ShipperDao shipperDao;
	
	@Autowired
	private PaymentDao paymentDao;
	@Override
	public OrderClass assignOrderToshipper(Shipper shipper,Integer orderId, Integer paymentId, String key)
			throws ShipperException, LoginException, VendorException, PaymentException {
		// TODO Auto-generated method stub
		
		Optional<OrderClass> orderOpt = orderDao.findById(orderId);
		Optional<Payment> paymentOpt = paymentDao.findById(paymentId);
		
		Payment payment = paymentOpt.get();
		OrderClass order = orderOpt.get();
		
		if(payment.getPaymentStatus().equals(PaymentStatus.UNSUCCESSFULLY)) {
			throw new PaymentException("Customer has not give payment till now sorry vendor waiting for customer to give payment");
		}
		
		if(order.getShipper().getShipperId() == shipper.getShipperId()) {
			throw new ShipperException("For this order Shipper is already assign ");
		}
		
		order.setShipper(shipper);
		List<OrderClass> orderList = shipper.getOrderClass();
		shipper.setOrderClass(orderList);
        shipperDao.save(shipper);
        return orderDao.save(order);
	}

}
