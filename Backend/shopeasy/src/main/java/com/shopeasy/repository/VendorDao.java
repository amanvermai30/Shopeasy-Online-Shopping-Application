package com.shopeasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopeasy.model.Vendor;

public interface VendorDao extends JpaRepository<Vendor, Integer>{

}
