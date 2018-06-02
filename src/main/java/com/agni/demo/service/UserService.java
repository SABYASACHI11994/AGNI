package com.agni.demo.service;

import com.agni.demo.data.Login;
import com.agni.demo.data.User;

public interface UserService
{

	Login login(User name) throws Exception;

	User saveu(User name);
	
	String activateUser(User name);
	
	Integer logout(String authkey);
}
