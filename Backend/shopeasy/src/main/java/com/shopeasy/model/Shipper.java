package com.shopeasy.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Shipper {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer shipperId;
	private String  shipperMobile;
	private String  companyName;
	

	@OneToMany(mappedBy = "shipper",cascade = CascadeType.ALL)
	private List<OrderClass> orderClass=new ArrayList<>(); 
}
