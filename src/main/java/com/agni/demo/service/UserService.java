package com.agni.demo.service;

import com.agni.demo.data.Login;

import java.security.NoSuchAlgorithmException;

import org.bson.types.ObjectId;

import com.agni.demo.data.CreateUserMap;
import com.agni.demo.data.User;

public interface UserService
{

	Login login(User name) throws Exception;

	CreateUserMap saveu(User name) throws Exception;
	
	CreateUserMap activateUser(ObjectId id) throws Exception;
	
	Integer logout(String authkey);

	CreateUserMap changePassword(User userdetails) throws Exception;
	
	CreateUserMap changeRole(User userdetails) throws Exception;

	String forgotPasswordMail(User userdetails) throws NoSuchAlgorithmException;

	String resetPassword(String name);

	
}
