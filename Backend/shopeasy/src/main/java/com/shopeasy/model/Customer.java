package com.shopeasy.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	 @OneToOne
	 private PersonalInfo personalInfo;
 
	 @OneToOne
	 private Cart cart;
	 
	 @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	 private List<Order1> order1 = new ArrayList<>();

}
