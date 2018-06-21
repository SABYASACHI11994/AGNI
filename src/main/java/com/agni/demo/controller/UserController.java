package com.agni.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.agni.demo.data.CreateUserMap;
import com.agni.demo.data.Login;
import com.agni.demo.data.User;
import com.agni.demo.service.UserService;
import com.agni.demo.util.OutputResponse;

@RestController
public class UserController
{
	ModelAndView mav = new ModelAndView();

	// private static final String template = "Hello, %s!";
	// private final AtomicLong counter = new AtomicLong();
	@Autowired
	UserService userService;

	@Autowired
	HttpServletResponse httpServletResponse;

	@CrossOrigin()
	@RequestMapping(value = "/login", method = { RequestMethod.POST }, produces = { "application/json" })
	public String login(@RequestBody User name)
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
	
	@CrossOrigin()
	@RequestMapping(value = "/logout", method = { RequestMethod.POST },headers = "Authorization", produces = { "application/json" })
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

	@CrossOrigin()
	@RequestMapping(value = "/registeruser", method = { RequestMethod.POST }, produces = { "application/json" })
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
	
	@CrossOrigin()
	@RequestMapping(value = "/activateuser/{name}", method = { RequestMethod.GET }, produces = { "application/json" })
	 public ModelAndView  acivateUser(@PathVariable("name")String name1)
	{
		
		
		OutputResponse response = new OutputResponse();
		try
		{			ObjectId name=new ObjectId(name1);
			CreateUserMap user = userService.activateUser(name);
			response.setResponse(user.toString());
			mav.addObject("activateSuccess", "activateSuccess");
	        mav.setViewName("../activateSuccess.html");
			
		} catch (Exception e)
		{
			e.printStackTrace();
			response.setError(e);
			mav.addObject("activateError", "activateError");
	        mav.setViewName("../activateError.html");
		}
		return mav;
	}
	
	@CrossOrigin()
	@RequestMapping(value = "/changepassword", method = { RequestMethod.POST },headers = "Authorization", produces = { "application/json" })
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
	
	@CrossOrigin()
	@RequestMapping(value = "/changerole", method = { RequestMethod.POST },headers = "Authorization", produces = { "application/json" })
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
	
	@CrossOrigin()
	@RequestMapping(value = "/forgotPasswordMail", method = { RequestMethod.POST }, produces = { "application/json" })
	public String forgotPasswordMail(@RequestBody User userdetails)
	{
		OutputResponse response = new OutputResponse();
		try
		{
			String user = userService.forgotPasswordMail(userdetails);
			response.setResponse(user.toString());
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setError(e);
		}
		return response.toString();
	}
	
	@CrossOrigin()
	@RequestMapping(value = "/resetPassword/{name}", method = { RequestMethod.GET }, produces = { "application/json" })
	public String resetPassword(@PathVariable("name")String name)
	{
		OutputResponse response = new OutputResponse();
		try
		{
			String user = userService.resetPassword(name);
			response.setResponse(user.toString());
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setError(e);
		}
		return response.toString();
	}
	
	@CrossOrigin()
	@RequestMapping(value = "/updateUser", method = { RequestMethod.GET },headers = "Authorization", produces = { "application/json" })
	public String updateUser(@RequestBody User name)
	{
		OutputResponse response = new OutputResponse();
		try
		{
			User user = userService.updateUser(name);
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