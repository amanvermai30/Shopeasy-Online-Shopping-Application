package com.shopeasy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopeasy.enums.CategoryType;
import com.shopeasy.model.Product;

public interface ProductDao extends JpaRepository<Product, Integer>{

	public List<Product> findByCategory(CategoryType categoryType);
}
