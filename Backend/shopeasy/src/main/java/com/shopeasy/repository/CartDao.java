package com.shopeasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopeasy.model.Cart;

public interface CartDao extends JpaRepository<Cart, Integer>{

}
