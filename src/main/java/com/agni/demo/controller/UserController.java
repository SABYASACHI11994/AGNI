package com.agni.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agni.demo.data.User;
import com.agni.demo.service.UserService;

@RestController
public class UserController {

	// private static final String template = "Hello, %s!";
	// private final AtomicLong counter = new AtomicLong();
	@Autowired
	UserService userService;

	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public User getLatestNews(@RequestBody User name) throws Exception {
		System.out.println(name);

		return userService.login(name);
	}
	
	@RequestMapping(value = "/user", method = { RequestMethod.POST })
	public User user(@RequestBody User name) {
		System.out.println(name);

		return userService.saveu(name);
	}
}