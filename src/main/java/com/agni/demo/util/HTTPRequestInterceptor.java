package com.agni.demo.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class HTTPRequestInterceptor extends HandlerInterceptorAdapter {

	Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		System.out.println("Request Object"+request.toString());
		return true;
	}

}