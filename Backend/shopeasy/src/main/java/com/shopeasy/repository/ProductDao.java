package com.shopeasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopeasy.model.Product;

public interface ProductDao extends JpaRepository<Product, Integer>{

}
