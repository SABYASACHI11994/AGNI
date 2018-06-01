package com.agni.demo.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class User
{
	@Id
	private String id;
	
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
	
	private Boolean isActive=false;
		
	List<String> role;

	private String createdBy;
	
	private Date createdDate=new Date();
	
	private String modifiedBy;
	
	private Date modifiedDate;
}
