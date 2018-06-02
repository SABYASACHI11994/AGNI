package com.agni.demo.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agni.demo.data.Login;
import com.agni.demo.data.Session;
import com.agni.demo.data.User;
import com.agni.demo.data.UserInterface;
import com.agni.demo.repo.SessionRepository;
import com.agni.demo.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SessionRepository sessionRepository;

	@Override
	public Login login(User name) throws Exception {
		// TODO Auto-generated method stub
		Login login=new Login();
		List<UserInterface> userdata = userRepository.findByEmail(name.getEmail());
		Optional<User> nameData=null;
		System.out.println(userdata);
		if (userdata.size() > 0) {
			if (userdata.get(0).getIsActive()) {
				if (checkPassword(name.getPassword(), userdata.get(0).getPassword())) {
					nameData = userRepository.findById(userdata.get(0).getId());
//					nameData.get().setCreatedDate(new Date());
//					nameData.get().setModifiedDate(new Date());
					String key=generateKey(nameData.toString()+new Date().toString());
					Session session =new Session();
					session.setUser_id(nameData.get().getId());
					session.setSessionKey(key);
					session.setRoles(nameData.get().getRole());
					session=sessionRepository.save(session);
					login.setUser(nameData.get());
					login.setAuthKey(key);
					
				}else{
					throw new Exception("Invalid Password");
				}
			}else{
				throw new Exception("User is not activated");
			}

		}else{
			throw new Exception("UserID doesnt exist");
		}
		
			System.out.println(nameData);	
		return login;
	}

	private boolean checkPassword(String password, String password2) {
		// TODO Auto-generated method stub

		return password.equals(password2);
	}

	@Override
	public User saveu(User name) {
		// TODO Auto-generated method stub

		return userRepository.save(name);
	}


	
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
	
	private String generateKey(String responseObj) throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		String key = null;
		byte[] bytes = md.digest(responseObj.getBytes());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++)
		{
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		key = sb.toString();
		return key;
	}

	@Override
	public Integer logout(String authkey) {
		// TODO Auto-generated method stub
		Integer i=0;
		Session ss=sessionRepository.findFirstBySessionKey(authkey);
		if(ss!=null){
			ss.setLogOutTime(new Date());
			ss.setIsActive(false);
			i=1;
		}
		return i;
	}
	
}
