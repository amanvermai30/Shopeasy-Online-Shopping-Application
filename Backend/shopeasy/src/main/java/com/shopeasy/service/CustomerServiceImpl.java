package com.shopeasy.service;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopeasy.exception.CartException;
import com.shopeasy.exception.CustomerException;
import com.shopeasy.exception.PersonalInfoException;
import com.shopeasy.exception.ProductException;
import com.shopeasy.model.Cart;
import com.shopeasy.model.CurrentSession;
import com.shopeasy.model.Customer;
import com.shopeasy.model.PersonalInfo;
import com.shopeasy.model.Product;
import com.shopeasy.model.ProductDTO;
import com.shopeasy.repository.CartDao;
import com.shopeasy.repository.CustomerDao;
import com.shopeasy.repository.PersonalInfoDao;
import com.shopeasy.repository.ProductDao;
import com.shopeasy.repository.SessionDao;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private PersonalInfoDao personalInfoDao;
	
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
	        PersonalInfo personalInfo = customer.getPersonalInfo();
	        personalInfo.setCustomer(savedCustomer);
	        personalInfoDao.save(personalInfo);

	        output = "Customer account was created successfully.";
	    } else {
	        throw new CustomerException("Error while creating customer account.");
	    }

	    return output;

	}

	@Override
	public List<Product> viewAllProduct(String key) throws ProductException, LoginException, CustomerException {
		// TODO Auto-generated method stub
		
		CurrentSession session = sessionDao.findByUuid(key);
		
		if(session == null ) {
			throw new LoginException("Login firt to see all product on shopeasy");
		}
		
        List<Product> products = productDao.findAll();
		
		if(products.isEmpty()) {
			throw new ProductException("Product is currently not available ");
		}
		return products;

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
	        
	       
            ProductDTO productDto = new ProductDTO();
            productDto.setCategory_type(product.getCategory_type());
            productDto.setDiscount(product.getDiscount());
            productDto.setPicture(product.getPicture());
            productDto.setPrice(product.getPrice());
            productDto.setProductDescription(product.getProductDescription());
            productDto.setProductId(productId);
            productDto.setProductName(product.getProductName());
            productDto.setQuantity(quantity);
            
	        List<ProductDTO> productList = cart.getProducts();
	        product.setQuantity(product.getQuantity()-quantity);
	        productList.add(productDto);
	        
//	        for(Product p : productList) {
//	        	
//	        	totalQ+=p.getQuantity();
//	        	totalP+=p.getPrice();
//	        }
	        
	        if(cart.getTotalPrice() == null && cart.getNumberOfProduct() == null ) {
	        	cart.setTotalPrice(0.0);
	        	cart.setNumberOfProduct(0);
	        }
	        
	        cart.setTotalPrice(cart.getTotalPrice()+ ((product.getPrice()-product.getPrice()*product.getDiscount()/100)*quantity ) );
	        cart.setNumberOfProduct(cart.getNumberOfProduct()+quantity);
	        cartDao.save(cart);
	        return cart;
	        
	    } else {
	        throw new CartException("Unable to add product to cart");
	    }
	}
	
}
