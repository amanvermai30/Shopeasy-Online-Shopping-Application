package com.shopeasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopeasy.model.Category;

public interface CategoryDao extends JpaRepository<Category, Integer>{

}
