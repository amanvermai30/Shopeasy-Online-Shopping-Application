package com.shopeasy.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	private String customerName;

	
//	Unidirectional mapping with personalInfo class
	 @OneToOne
	 @JoinColumn(name="customerId")
	 private PersonalInfo personalInfo;
 
//  Bidirectional mapping with cart class which will have customerId foreign key 
	 @OneToOne(mappedBy = "customer")
	 private Cart cart;
	 
	 @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	 private List<Order1> order1 = new ArrayList<>();

}
