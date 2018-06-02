package com.agni.demo.service;

import com.agni.demo.data.User;

public interface UserService
{

	User login(User name);

	User saveu(User name);
	
	String activateUser(User name);
	
	
}
