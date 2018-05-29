package com.agni.demo.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Session
{
	@Id
	private int id;
	
	private int user_id;
	
	private String ipAddress;
	
	private Date loginTime;
	
	private Date activeSince;
	
	private Date expiryTime;
	
	private String sessionKey;
	
	private String createdBy;
	
	private Date createdDate;
	
	private String modifiedBy;
	
	private Date modifiedDate;
}
