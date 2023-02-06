package com.shopeasy.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonalInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer personalId;
	private String phone;
	private String email;
	private String password;
	private String country;
	private String city;
	private String pincode;

}
