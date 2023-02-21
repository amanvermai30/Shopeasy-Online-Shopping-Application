package com.shopeasy.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

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
public class Vendor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private Integer vendorId;
	 private String venderName;
	 

//	 Many to many with product class 
	 @ManyToMany(mappedBy = "vendors",cascade = CascadeType.ALL)
	 List<Product> products=new ArrayList<>();
	 
//	 One to One bidirectional with personalInfo 
	 @OneToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name = "info_id")
	 private PersonalInfo personalInfo;

}
