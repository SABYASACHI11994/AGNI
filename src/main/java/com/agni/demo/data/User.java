package com.agni.demo.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class User
{
	@Id
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String gender;
	
	private Date dOB;
	
	private String email;
	
	private String phoneNumber;
	
	private String address;
	
	private String userName;
	
	private String domain;
	
	private String password;
	
	private Boolean isActive;
		
	List<String> role;

	private String createdBy;
	
	private Date createdDate;
	
	private String modifiedBy;
	
	private Date modifiedDate;
}
