package com.agni.demo.service;

import com.agni.demo.data.CreateUserMap;
import com.agni.demo.data.User;

public interface UserService
{

	User login(User name) throws Exception;

	CreateUserMap saveu(User name) throws Exception;
	
	String activateUser(User name);
	
	
}
