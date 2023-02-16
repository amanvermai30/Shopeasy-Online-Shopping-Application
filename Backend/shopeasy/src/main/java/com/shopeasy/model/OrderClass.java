package com.shopeasy.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shopeasy.dto.ProductDTO;
import com.shopeasy.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderClass {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	
    private LocalDate orderCreatedAt;
    private LocalDate deliveryDate;
	private Double totalAmount;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus = OrderStatus.NOTSHIPPED;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="address_id")
	private Address deliveryAddress;
	

	@Embedded
	@ElementCollection
	private List<ProductDTO> products=new ArrayList<>();
	
	
//	@OneToOne(mappedBy = "orderClass")
//	private Payment payment;
	
	
	@ManyToOne()
	@JoinColumn(name = "customerId")
	private Customer customer;
	
	
//	@ManyToOne
//	@JoinColumn(name="shipperId")
//	private Shipper shipper;
}
