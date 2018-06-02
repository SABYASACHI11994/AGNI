package com.agni.demo.service;

import org.bson.types.ObjectId;

import com.agni.demo.data.CreateUserMap;
import com.agni.demo.data.User;

public interface UserService
{

	User login(User name) throws Exception;

	CreateUserMap saveu(User name) throws Exception;
	
	CreateUserMap activateUser(ObjectId id) throws Exception;
	
	CreateUserMap changePassword(User userdetails) throws Exception;

	
}
