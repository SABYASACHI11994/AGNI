package com.agni.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agni.demo.data.CreateUserMap;
import com.agni.demo.data.Login;
import com.agni.demo.data.User;
import com.agni.demo.service.UserService;
import com.agni.demo.util.OutputResponse;

@RestController
public class UserController
{

	// private static final String template = "Hello, %s!";
	// private final AtomicLong counter = new AtomicLong();
	@Autowired
	UserService userService;

	@Autowired
	HttpServletResponse httpServletResponse;

	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public String getLatestNews(@RequestBody User name)
	{
		System.out.println(name);
		
		OutputResponse response=new OutputResponse();
		try {
			Login user = userService.login(name);
			response.setResponse(user.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setError(e);
		}
		return response.toString();
	}
	
	@RequestMapping(value = "/logout", method = { RequestMethod.POST })
	public String logout(HttpServletRequest httpRequest)  {
//		System.out.println(name);
		
		OutputResponse response=new OutputResponse();
		try {
			Integer user = userService.logout(httpRequest.getHeader("authorization"));
			response.setResponse(user.toString());
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setError(e);
		}
		return response.toString();
	}

	@RequestMapping(value = "/registeruser", method = { RequestMethod.POST })
	public String user(@RequestBody User name)
	{
		OutputResponse response = new OutputResponse();
		try
		{
			CreateUserMap user = userService.saveu(name);
			response.setResponse(user.toString());
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setError(e);
		}
		return response.toString();
	}
	
	
	@RequestMapping(value = "/activateuser/{name}", method = { RequestMethod.GET })
	 public String acivateUser(@PathVariable("name")ObjectId name)
	{
		
		OutputResponse response = new OutputResponse();
		try
		{			
			CreateUserMap user = userService.activateUser(name);
			response.setResponse(user.toString());
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setError(e);
		}
		return response.toString();
	}
	
	
	@RequestMapping(value = "/changepassword", method = { RequestMethod.POST })
	public String changePassword(@RequestBody User userdetails)
	{
		OutputResponse response = new OutputResponse();
		try
		{
			CreateUserMap user = userService.changePassword(userdetails);
			response.setResponse(user.toString());
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setError(e);
		}
		return response.toString();
	}
	
	@RequestMapping(value = "/changerole", method = { RequestMethod.POST })
	public String changeRole(@RequestBody User userdetails)
	{
		OutputResponse response = new OutputResponse();
		try
		{
			CreateUserMap user = userService.changeRole(userdetails);
			response.setResponse(user.toString());
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setError(e);
		}
		return response.toString();
	}
	
}