package com.shopeasy.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shopeasy.enums.CategoryType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	
//	@NotNull(message = "password cannot set as null")
//    @NotEmpty(message =  "password cannot set as empty")
//	@NotBlank(message =  "password cannot set as blank")
	private String productName;
	
//	@NotNull(message = "password cannot set as null")
//    @NotEmpty(message =  "password cannot set as empty")
//	@NotBlank(message =  "password cannot set as blank")
	private String picture;
	
//	@DecimalMin(value = "1", inclusive = true, message = "Price must be greater than 0")
	private Integer quantity;
	
//	@DecimalMin(value = "1", inclusive = true, message = "Price must be greater than 0")
	private Double marketPrice;
	
//	@DecimalMin(value = "1", inclusive = true, message = "Price must be greater than 0")
	private Double AfterDiscountPrice;
	

	private String productDescription;
	
	private Double discount; 
	
	@Enumerated(EnumType.STRING)
	private CategoryType category;

	@JsonIgnore
	@ManyToMany
	 @JoinTable(
			    name = "vendor_product", 
			    joinColumns = @JoinColumn(name = "productId"), 
			    inverseJoinColumns = @JoinColumn(name = "vendorId")
			  )
	private List<Vendor> vendors=new ArrayList<>();
	
}
