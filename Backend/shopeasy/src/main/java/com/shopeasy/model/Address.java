package com.shopeasy.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressId;
	
	@NotNull(message = "Country cannot set as null")
    @NotEmpty(message =  "Country cannot set as empty")
	@NotBlank(message =  "Country cannot set as blank") 
	private String country;
	
	@NotNull(message = "City cannot set as null")
    @NotEmpty(message =  "City cannot set as empty")
	@NotBlank(message =  "City cannot set as blank") 
	private String city;
	
	@NotNull(message = "streetNo cannot set as null")
    @NotEmpty(message =  "streetNo cannot set as empty")
	@NotBlank(message =  "streetNo cannot set as blank") 
	private String streetNo;
	
	@NotNull(message = "streetNo cannot set as null")
    @NotEmpty(message =  "streetNo cannot set as empty")
	@NotBlank(message =  "streetNo cannot set as blank") 
	private String buildingName;
	
	@NotNull(message = "pincode cannot set as null")
    @NotEmpty(message =  "pincode name cannot set as empty")
	@NotBlank(message =  "pincode name cannot set as blank")
	@Size(min = 6 ,max = 6 ,message = "pincode is Must Be 6 digit")
	private String pincode;
	
	@OneToOne(mappedBy = "address",cascade = CascadeType.ALL)
	private Customer customer;
	
	@OneToOne(mappedBy = "deliveryAddress",cascade = CascadeType.ALL)
	private OrderClass order;

}
