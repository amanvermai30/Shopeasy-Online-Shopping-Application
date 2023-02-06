package com.shopeasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopeasy.model.Payment;

public interface PaymentDao extends JpaRepository<Payment, Integer>{

}
