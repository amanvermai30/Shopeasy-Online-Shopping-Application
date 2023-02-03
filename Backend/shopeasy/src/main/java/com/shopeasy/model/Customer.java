package com.shopeasy.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

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
	
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Integer id;
	 private String name;
	 private String password;
	 private String mobile;
	 private String email;
	 
	 @OneToMany(mappedBy="customer")
	 private List<Address> address = new ArrayList<>();

	 @OneToMany(mappedBy="customer")
	 private List<CustomerBankDetails> customerBank = new ArrayList<>();

}
