package com.shopeasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopeasy.model.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer>{

}
