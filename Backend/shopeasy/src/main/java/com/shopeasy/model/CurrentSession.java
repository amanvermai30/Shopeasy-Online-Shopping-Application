package com.shopeasy.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CurrentSession {

	@Id
	@Column(unique = true)
	private Integer Id;
	
	private String uuid;
	
	private LocalDateTime timeStamp;

	@Enumerated(EnumType.STRING)
	private UserType user_type;
}
