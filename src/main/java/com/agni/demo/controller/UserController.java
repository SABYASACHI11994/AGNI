package com.agni.demo.controller;

import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agni.demo.data.CreateUserMap;
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

		OutputResponse response = new OutputResponse();
		try
		{
			User user = userService.login(name);
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
		System.out.println(name);
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
	
	
	@RequestMapping(value = "/activateUser/{name}", method = { RequestMethod.GET })
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
	
	
	
	
}