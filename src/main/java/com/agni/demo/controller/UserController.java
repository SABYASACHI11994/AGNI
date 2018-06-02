package com.agni.demo.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agni.demo.data.Login;
import com.agni.demo.data.User;
import com.agni.demo.service.UserService;
import com.agni.demo.util.OutputResponse;

@RestController
public class UserController {

	// private static final String template = "Hello, %s!";
	// private final AtomicLong counter = new AtomicLong();
	@Autowired
	UserService userService;
	
	@Autowired
	HttpServletResponse httpServletResponse;

	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public String getLatestNews(@RequestBody User name)  {
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
	public String logout(@RequestBody Login name)  {
		System.out.println(name);
		
		OutputResponse response=new OutputResponse();
		try {
			Integer user = userService.logout(name.getAuthKey());
			response.setResponse(user.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setError(e);
		}
		return response.toString();
	}
	
	@RequestMapping(value = "/user", method = { RequestMethod.POST })
	public String user(@RequestBody User name) {
		System.out.println(name);
		OutputResponse response=new OutputResponse();
		try {
			User user = userService.saveu(name);
			response.setResponse(user.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setError(e);
		}
		return response.toString();
	}
}