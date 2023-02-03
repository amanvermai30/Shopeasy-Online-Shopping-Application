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
public class CustomerBankDetails {
	
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private Integer id;
	  private String bankName;
	  private String accountNo;
	  private String IFSC;
	  private String branchName;
	  
	  @ManyToOne
	  @JoinColumn(name="customer_id")
	  private Customer customer;

}
