package com.agni.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agni.demo.data.User;
import com.agni.demo.data.UserInterface;
import com.agni.demo.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService
{

	@Autowired
	UserRepository userRepository;

	@Override
	public User login(User name) {
		// TODO Auto-generated method stub
		List<UserInterface> userdata=userRepository.findByEmail(name.getEmail());
		System.out.println(userdata);
		return null;
	}
	
	@Override
	public User saveu(User name) {
		// TODO Auto-generated method stub
		
		return userRepository.save(name);
	}

	@Override
	public String activateUser(User name)
	{
		List<UserInterface> userpartialdata = userRepository.findByEmail(name.getEmail());
		
		if(userpartialdata.isEmpty()){
			
			return "User Not Found";
			
		}else
		// userpartialdata.get(0).getisActive()
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
