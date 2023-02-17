package com.shopeasy.dto;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.shopeasy.enums.OrderStatus;
import com.shopeasy.model.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDTO {
	
    private Integer orderId;
    private LocalDate orderCreatedAt;
    private LocalDate deliveryDate;
	private Double totalAmount;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus = OrderStatus.NOTSHIPPED;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="address_id")
	private Address deliveryAddress;

}
