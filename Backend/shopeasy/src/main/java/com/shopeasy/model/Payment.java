package com.shopeasy.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shopeasy.enums.PaymentMethod;
import com.shopeasy.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer paymentId;
	
	@Enumerated(EnumType.STRING)
	private PaymentMethod method;  
	
	private LocalDate paymentCreatedAtDate;

     private Double paymentAmount;
     
     @Enumerated(EnumType.STRING)
     private PaymentStatus paymentStatus;
     
//  Bidirectional mapping with payment class which and foreign key orderId
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId")
    private OrderClass orders;
}
