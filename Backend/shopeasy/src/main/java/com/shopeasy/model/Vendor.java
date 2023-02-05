package com.shopeasy.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
public class Vendor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private Integer vendorId;
	 private String venderName;
	 

	 @ManyToMany()
	 List<Product> products=new ArrayList<>();
	 
	 
	 @OneToOne
	 private PersonalInfo personalInfo;

}
