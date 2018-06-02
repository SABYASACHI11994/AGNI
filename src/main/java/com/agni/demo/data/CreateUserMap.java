package com.agni.demo.data;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Transient;

import com.agni.demo.util.OutputMapper;
import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class CreateUserMap
{
	@Expose
	ObjectId id;

	@Expose
	String firstName;
	
	@Expose
	String lastName;
	
	@Expose
	String email;
	
	@Expose
	Boolean isActive;
	
	@Transient
	private OutputMapper outputMapper = new OutputMapper();

	@Override
	public String toString() {
		return outputMapper.gson().toJson(this);
	}
	
	
}
