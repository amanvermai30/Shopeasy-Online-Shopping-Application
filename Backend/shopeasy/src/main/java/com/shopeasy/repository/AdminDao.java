package com.shopeasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopeasy.model.Admin;
import com.shopeasy.model.Vendor;

public interface AdminDao extends JpaRepository<Admin, Integer>{

	public Admin findByEmail(String email);
}
