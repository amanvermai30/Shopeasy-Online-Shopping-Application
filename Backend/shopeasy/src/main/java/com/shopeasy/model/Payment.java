package com.shopeasy.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer paymentId;
	
	@Enumerated(EnumType.STRING)
	private PaymentMethod method;  
	
	private LocalDate paymentDate;

	private LocalTime paymentTime;

     private Double paymentAmount;
     
//  Bidirectional mapping with payment class which and foreign key orderId
    @OneToOne
    @JoinColumn(name="orderId")
    private Order1 order1;
}
