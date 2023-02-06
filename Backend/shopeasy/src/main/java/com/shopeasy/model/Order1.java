package com.shopeasy.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order1 {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
    private LocalDate date;
    private LocalTime time;
	private Double totalAmount;
	

	@OneToMany(mappedBy = "order1",cascade = CascadeType.ALL)
	private List<Product> products=new ArrayList<>();
	
	
	@OneToOne(mappedBy = "order1")
	private Payment payment;
	
	
	@ManyToOne()
	@JoinColumn(name = "customerId")
	private Customer customer;
	
	
	@ManyToOne
	@JoinColumn(name="shipperId")
	private Shipper shipper;
}
