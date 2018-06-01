package com.agni.demo.service;

import com.agni.demo.data.User;

public interface UserService
{

	User login(User name) throws Exception;

	User saveu(User name);
	
	
}
