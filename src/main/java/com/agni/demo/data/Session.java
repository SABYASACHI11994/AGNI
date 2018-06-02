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
public class Session
{
	@Id
	@Expose
	private ObjectId id;
	@Expose
	private ObjectId user_id;
	@Expose
	private String ipAddress;
	@Expose
	private Date logInTime=new Date();
	@Expose
	private Date logOutTime;
	@Expose
	private Date activeSince=new Date();
	@Expose
	private Date expiryTime;
	@Expose
	private List<String> roles;
	@Expose
	private String sessionKey;
	@Expose
	private Boolean isActive=true;
	@Expose
	private String createdBy;
	@Expose
	private Date createdDate=new Date();
	@Expose
	private String modifiedBy;
	@Expose
	private Date modifiedDate;
	
	@Transient
	private OutputMapper outputMapper = new OutputMapper();

	@Override
	public String toString() {
		return outputMapper.gson().toJson(this);
	}
}
