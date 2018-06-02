package com.agni.demo.data;

import org.bson.types.ObjectId;

public interface  UserInterface {

	
	ObjectId getId();
	
	String getEmail();
	
	String getPassword();
	

	Boolean getIsActive();
}
