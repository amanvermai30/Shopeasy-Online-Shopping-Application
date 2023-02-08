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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@NotNull(message = "name cannot set as null")
    @NotEmpty(message =  "name cannot set as empty")
	@NotBlank(message =  "name cannot set as blank")
	private String customerName;

	
//	Unidirectional mapping with personalInfo class
//	 @OneToOne( mappedBy = "customer", cascade = CascadeType.ALL)
//	 @JoinColumn(name="customerId")
	 @OneToOne
	 private PersonalInfo personalInfo;
 
//  Bidirectional mapping with cart class which will have customerId foreign key
	 @JsonIgnore
	 @OneToOne(mappedBy = "customer")
	 private Cart cart;
	 
	 @JsonIgnore
	 @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	 private List<OrderClass> orderClass = new ArrayList<>();

}
