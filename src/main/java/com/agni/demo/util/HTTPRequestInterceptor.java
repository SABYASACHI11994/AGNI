package com.agni.demo.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.agni.demo.data.Session;
import com.agni.demo.repo.SessionRepository;

@Component
public class HTTPRequestInterceptor extends HandlerInterceptorAdapter {

	Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Autowired
	SessionRepository sessionRepository;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		System.out.println("Request Object"+request.toString());
		
		String authkey = request.getHeader("authorization");
		boolean ret=false;
		
		String[] requestURIParts = request.getRequestURI().split("/");
		logger.info(requestURIParts+" logging prehandle 1");
		if(requestURIParts.length>2){
			String requestAPI = requestURIParts[2];	
			System.out.println(requestAPI);
			logger.info(requestAPI+" logging prehandle");
			switch (requestAPI) {
			case "registeruser":
			case "login":
			case "logout":
			case "activateuser":
			case "swagger-ui.html":
			case "webjars":
			case "swagger-resources":
			case "v2":
			case "error":
			case "activateSuccess":
			case "forgotPasswordMail":
			case "resetPassword":
			case "activateSuccess.html":
				ret=true;
				break;

			default:
				Session ss=sessionRepository.findFirstBySessionKey(authkey);
				ret=checkActive(ss);
				if(ret){
					ret=checkPrivilege(ss,requestAPI);
					if(ret){
						ss.setActiveSince(new Date());
						sessionRepository.save(ss);	
					}
				}
					
					
				
				break;
			}
		}else{
			ret=true;
		}
		
//		for (int i = 0; i < requestURIParts.length; i++) {
//			System.out.println(requestURIParts[i]);
//		} 
//		System.out.println(request.getRequestURI());
		if(!ret){
			response.setStatus(5000, "Invalid Authentication");
			throw new LoginException("Invalid Authentication");
		}
		
		return ret;
	}
	private boolean checkPrivilege(Session ss,String path) {
		// TODO Auto-generated method stub
		return true;
	}
	private boolean checkActive(Session ss) {
		// TODO Auto-generated method stub
		return ss.getIsActive();
	}

}