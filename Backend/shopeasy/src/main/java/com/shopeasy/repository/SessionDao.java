package com.shopeasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopeasy.model.CurrentSession;

public interface SessionDao extends JpaRepository<CurrentSession, Integer>{

	public CurrentSession findByUuid(String uuid);
}
