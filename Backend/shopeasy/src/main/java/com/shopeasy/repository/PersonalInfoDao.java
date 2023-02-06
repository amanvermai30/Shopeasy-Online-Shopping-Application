package com.shopeasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopeasy.model.PersonalInfo;

public interface PersonalInfoDao extends JpaRepository<PersonalInfo, Integer>{

}
