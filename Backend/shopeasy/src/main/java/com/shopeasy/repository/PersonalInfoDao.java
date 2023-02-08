package com.shopeasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopeasy.model.Customer;
import com.shopeasy.model.PersonalInfo;
import com.shopeasy.model.Vendor;

public interface PersonalInfoDao extends JpaRepository<PersonalInfo, Integer>{

	public Customer findByEmail(String email);
	public Vendor findVendorByEmail(String email);
}
