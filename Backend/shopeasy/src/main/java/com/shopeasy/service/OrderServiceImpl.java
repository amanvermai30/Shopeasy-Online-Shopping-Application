package com.shopeasy.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopeasy.dto.ProductDTO;
import com.shopeasy.exception.CartException;
import com.shopeasy.exception.CustomerException;
import com.shopeasy.exception.OrderClassException;
import com.shopeasy.exception.ProductException;
import com.shopeasy.model.Cart;
import com.shopeasy.model.Customer;
import com.shopeasy.model.OrderClass;
import com.shopeasy.repository.CustomerDao;
import com.shopeasy.repository.OrderClassDao;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private OrderClassDao orderDao;
	
	@Override
	public OrderClass giveYourOrder(OrderClass order, Integer customerId)
			throws LoginException, CustomerException, CartException, ProductException, OrderClassException {
		// TODO Auto-generated method stub
		
		Optional<Customer> customerOpt = customerDao.findById(customerId);
		Customer customer = customerOpt.get();
		
		if(customer == null ) {
			throw new CustomerException("Customer not found");
		}
		
		Cart cart = customer.getCart();
		List<ProductDTO> products =  cart.getProducts();
		
		if(products.isEmpty()) {
			throw new ProductException("Product not found in your cart ");
			
		}else {
			
			order.setCustomer(customer);
			order.setDeliveryAddress(order.getDeliveryAddress());
			order.setDeliveryDate(order.getDeliveryDate().plusDays(1));
			order.setOrderCreatedAt(LocalDate.now());
			order.setProducts(products);
			order.setTotalAmount(cart.getTotalPrice());
			customer.getOrderClass().add(order);
			return orderDao.save(order);
			
		}

	}

	@Override
	public OrderClass updateYourOrder(OrderClass order, String key)
			throws LoginException, CustomerException, CartException, ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderClass deleteYourOrder(OrderClass order, String key)
			throws LoginException, CustomerException, CartException, ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderClass> viewAllOrderByUserId(Integer userid) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

}
