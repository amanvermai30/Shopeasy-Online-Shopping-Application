package com.shopeasy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {
	
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private Integer id;
	  private String country;
	  private String state;
	  private String city;
	  private String pincode;
	  private String landmark;
	  private String houseNumber;
	  private String buildingName;
	  
	  @ManyToOne
	  @JoinColumn(name="customer_id")
	  private Customer customer;

}
