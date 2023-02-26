package com.shopeasy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.shopeasy.enums.CategoryType;
import com.shopeasy.model.Product;

public interface ProductDao extends JpaRepository<Product, Integer>{

	public List<Product> findByCategory(CategoryType categoryType);
	
	@Modifying
    @Query("DELETE FROM Product p WHERE p.productId = ?1")
	public void deleteProductById(int productId);
	
	@Modifying
	@Query("UPDATE Product p SET p.quantity = :quantity WHERE p.productId = :productId")
	public void updateProductQuantityById(Integer productId, Integer quantity);

}
