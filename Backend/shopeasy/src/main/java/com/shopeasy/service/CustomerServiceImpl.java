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
public class CustomerServiceImpl implements CustomerService {

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
	public String createCustomerAccount(Customer customer) throws CustomerException, PersonalInfoException {
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
			if (product.getQuantity() == 0) {
				throw new ProductException("Sorry customer product is currently out of stock");
			}

			if (product.getQuantity() < quantity) {
				throw new ProductException("Sorry currently for your selected product " + product.getProductName()
						+ " available quantity is " + product.getQuantity()
						+ " so please enter quantity lesser than or equal to available quantity");
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
			for (ProductDTO p : productList) {
			    if (p.getProductId() == productId) {
			        throw new CartException("Product is already in cart");
			    }
			}
			
			productList.add(productDto);

			if (cart.getTotalPrice() == null && cart.getNumberOfProduct() == null) {
				cart.setTotalPrice(0.0);
				cart.setNumberOfProduct(0);
			}

//	        giving logic for discount and final price 
			cart.setTotalPrice(cart.getTotalPrice() + (product.getAfterDiscountPrice()*quantity));
			cart.setNumberOfProduct(cart.getNumberOfProduct() + quantity);
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

		if (loggedInUser == null)
			throw new CustomerException("Please enter valid key");

		if (customer.getCustomerId() == loggedInUser.getId()) {
			return customerDao.save(customer);
		} else {
			throw new CustomerException("Invalid customer details, please login first!");
		}
	}

	@Override
	public Cart deleteProductFromCart(Integer productId, Integer customerId, String key)
			throws CartException, ProductException, CustomerException {
		
		Optional<Customer> customerOpt = customerDao.findById(customerId);

		if (customerOpt.isPresent()) {
			
			Customer customer = customerOpt.get();
			Cart cart = customer.getCart();
			
			List<ProductDTO> products = cart.getProducts();

			// Find the product with the matching ID and remove it from the cart
			ProductDTO productToRemove = null;
			for (ProductDTO product : products) {
				if (product.getProductId() == productId) {
					productToRemove = product;
					break;
				}
			}

			if (productToRemove != null) {
				
				int quantity = productToRemove.getQuantity();
				double price = productToRemove.getAfterDiscountPrice();

				// Update the cart total price and number of products
				cart.setTotalPrice(Math.abs( cart.getTotalPrice() - (price * quantity) ));
				cart.setNumberOfProduct(cart.getNumberOfProduct() - quantity);

				// Remove the product from the cart
				products.remove(productToRemove);

				customer.setCart(cart);
				customerDao.save(customer);
				return cart;
				
			} else {
				throw new ProductException("Product not found in cart");
			}
		} else {
			throw new CustomerException("Customer not found");
		}
	}

	@Override
	public Cart getCartDetails(Integer customerId) throws CartException, ProductException, CustomerException {
		// TODO Auto-generated method stub
		
		Optional<Customer> cartOpt = customerDao.findById(customerId);
		
		if(cartOpt.isPresent()) {
			
			Customer customer = cartOpt.get();
			
			Cart cart = customer.getCart();
			
			if(cart == null ) {
				throw new CartException("there is not product in cart");
				
			}else {
				return cart;
			}
			
		}else {
			throw new CustomerException("customer not found with this given id");
		}
	}

}
