package com.shopeasy.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	

	@OneToMany()
	private List<Product> products=new ArrayList<>();
	
	
	@OneToOne()
	private Payment payment;
	
	
	@ManyToOne()
	private Customer customer;
	
	
	@ManyToOne()
	private Shipper shipper;
}
