package com.shopeasy.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopeasy.dto.ProductDTO;
import com.shopeasy.exception.CartException;
import com.shopeasy.exception.CustomerException;
import com.shopeasy.exception.PersonalInfoException;
import com.shopeasy.exception.ProductException;
import com.shopeasy.model.Address;
import com.shopeasy.model.Cart;
import com.shopeasy.model.CurrentSession;
import com.shopeasy.model.Customer;
import com.shopeasy.model.Product;
import com.shopeasy.repository.AddressDao;
import com.shopeasy.repository.CartDao;
import com.shopeasy.repository.CustomerDao;
import com.shopeasy.repository.ProductDao;
import com.shopeasy.repository.SessionDao;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private SessionDao sessionDao;
	
	@Autowired
	private CartDao cartDao;
	
	
	@Override
	public String createCustomerAccount(Customer customer) throws CustomerException,PersonalInfoException {
		// TODO Auto-generated method stub
		
		String output = "Customer account was not created.";

	    Customer savedCustomer = customerDao.save(customer);
	    if (savedCustomer != null) {
	    	
	        Address address = savedCustomer.getAddress();
	        address.setCustomer(savedCustomer);
	        addressDao.save(address);
	        output = "Customer account is created successfully.";
	        
	    } else {
	        throw new CustomerException("Error while creating customer account.");
	    }

	    return output;

	}
	

	@Override
	public Cart addProductsToCart(Integer productId, Integer customerId, Integer quantity)
			throws CartException, ProductException, CustomerException {
		// TODO Auto-generated method stub
		
		Optional<Product> productOpt = productDao.findById(productId);
	    Optional<Customer> customerOpt = customerDao.findById(customerId);

	    if (customerOpt.isPresent() && productOpt.isPresent()) {
	        Customer customer = customerOpt.get();
	        Product product = productOpt.get();

	        Cart cart = customer.getCart();
	        if (cart == null) {
	            cart = new Cart();
	            cart.setCustomer(customer);
	        }
	        
//	        checking for available product and required quantity no
	        if(product.getQuantity() == 0 ) {
	        	throw new ProductException("Sorry customer product is currently out of stock");
	        }
	        
	        if(product.getQuantity() < quantity) {
	        	throw new ProductException("Sorry currently for your selected product "+product.getProductName()+" available quantity is "+product.getQuantity()+" so please enter quantity lesser than or equal to available quantity");
	        }
	        
	       
            ProductDTO productDto = new ProductDTO();
            productDto.setCategory(product.getCategory());
            productDto.setDiscount(product.getDiscount());
            productDto.setPicture(product.getPicture());
            productDto.setMarketPrice(product.getMarketPrice());
            productDto.setAfterDiscountPrice(product.getAfterDiscountPrice());
            productDto.setProductDescription(product.getProductDescription());
            productDto.setProductId(productId);
            productDto.setProductName(product.getProductName());
            productDto.setQuantity(quantity);
            
	        List<ProductDTO> productList = cart.getProducts();
//	        product.setQuantity(product.getQuantity()-quantity);
	        productList.add(productDto);
	        
	        
	        if(cart.getTotalPrice() == null && cart.getNumberOfProduct() == null ) {
	        	cart.setTotalPrice(0.0);
	        	cart.setNumberOfProduct(0);
	        }
	        
//	        giving logic for discount and final price 
	        cart.setTotalPrice(cart.getTotalPrice()+ ((product.getMarketPrice())-(product.getMarketPrice()*product.getDiscount()/100)*quantity ) );
	        cart.setNumberOfProduct(cart.getNumberOfProduct()+quantity);
	        cartDao.save(cart);
	        return cart;
	        
	    } else {
	        throw new CartException("Unable to add product to cart");
	    }
	}

	@Override
	public Customer updateCustomer(Customer customer, String key) throws CustomerException {
		// TODO Auto-generated method stub
		
        CurrentSession loggedInUser = sessionDao.findByUuid(key);
		
		if(loggedInUser == null)
			throw new CustomerException("Please enter valid key");
		
		
		if(customer.getCustomerId() == loggedInUser.getId()) {
			return customerDao.save(customer);
		}
		else {
			throw new CustomerException("Invalid customer details, please login first!");
		}
	}

}
