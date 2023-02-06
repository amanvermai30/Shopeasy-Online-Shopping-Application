package com.shopeasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopeasy.model.Admin;

public interface AdminDao extends JpaRepository<Admin, Integer>{

}
