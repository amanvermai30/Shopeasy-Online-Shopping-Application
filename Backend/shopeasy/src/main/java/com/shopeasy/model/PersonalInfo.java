package com.shopeasy.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonalInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer personalId;
	
	@NotNull(message = "mobile cannot set as null")
    @Pattern(regexp = "^[6-9]\\d{9}$")
	private String phone;
	
	@Column(unique = true)
	@Email(message = "email format is incorrect")
	private String email;
	
	@NotNull(message = "password cannot set as null")
    @NotEmpty(message =  "password cannot set as empty")
	@NotBlank(message =  "password cannot set as blank")
	@Size(min = 6 ,max = 6 ,message = "password is Must Be 6 digit")
	private String password;
	
	@NotNull(message = "password cannot set as null")
    @NotEmpty(message =  "password cannot set as empty")
	@NotBlank(message =  "password cannot set as blank")
	private String imageUrl;
	
	@NotNull(message = "image cannot set as null")
    @NotEmpty(message =  "image cannot set as empty")
	@NotBlank(message =  "image cannot set as blank")
	private String country;
	
	@NotNull(message = "image cannot set as null")
    @NotEmpty(message =  "image cannot set as empty")
	@NotBlank(message =  "image cannot set as blank")
	private String state;
	
	@NotNull(message = "image cannot set as null")
    @NotEmpty(message =  "image cannot set as empty")
	@NotBlank(message =  "image cannot set as blank")
	private String city;
	
	@NotNull(message = "image cannot set as null")
    @NotEmpty(message =  "image cannot set as empty")
	@NotBlank(message =  "image cannot set as blank")
	private String pincode;
	
	@JsonIgnore
	@OneToOne(mappedBy="personalInfo",cascade = CascadeType.ALL)
	private Vendor vendor;

}
