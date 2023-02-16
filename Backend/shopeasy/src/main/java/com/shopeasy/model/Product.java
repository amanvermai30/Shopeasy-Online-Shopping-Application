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
	private String productName;
	private String picture;
	private Integer quantity;
	private Double price;
	private String productDescription;
	private Double discount; 
	
	@Enumerated(EnumType.STRING)
	private CategoryType category_type;

	@ManyToMany
	 @JoinTable(
			    name = "vendor_product", 
			    joinColumns = @JoinColumn(name = "productId"), 
			    inverseJoinColumns = @JoinColumn(name = "vendorId")
			  )
	private List<Vendor> vendors=new ArrayList<>();
	
}
