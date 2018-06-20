package com.agni.demo.data;

import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import com.agni.demo.util.OutputMapper;
import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class User
{
	@Id
	@Expose
	private ObjectId id;
	
	@Expose
	private String firstName;
	
	@Expose
	private String lastName;
	
	@Expose
	private String gender;
	
	@Expose
	private Date dOB;
	
	@Expose
	private String email;
	
	@Expose
	private String phoneNumber;
	
	@Expose
	private String address;
	
	@Expose
	private String userName;
	
	@Expose
	private String domain;
	
	private String password;
	
	@Transient
	private String newPassword;
	
	private Boolean isActive=false;
		
	@Expose
	List<String> role;

	private String createdBy;
	
	private Date createdDate=new Date();
	
	private String modifiedBy;
	
	private Date modifiedDate;
	
	@Expose
	private String profileImage;
	
	@Expose
	private String resetPassword;
	
	@Transient
	private OutputMapper outputMapper = new OutputMapper();

	@Override
	public String toString() {
		return outputMapper.gson().toJson(this);
	}
	
	
}
