package com.shopeasy.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
	
	
	@ManyToOne()
	private Category category;

	@ManyToMany
	private List<Vendor> vendors=new ArrayList<>();
	
	@ManyToOne
	private Cart cart;
	
	
    @ManyToOne()
    private Order1 order;
}
