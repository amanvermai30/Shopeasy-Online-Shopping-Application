package com.shopeasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopeasy.model.Address;

public interface AddressDao extends JpaRepository<Address, Integer>{

}
