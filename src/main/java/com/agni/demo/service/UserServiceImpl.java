package com.agni.demo.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.RandomStringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.agni.demo.data.CreateUserMap;
import com.agni.demo.data.Login;
import com.agni.demo.data.Session;
import com.agni.demo.data.User;
import com.agni.demo.data.UserInterface;
import com.agni.demo.repo.SessionRepository;
import com.agni.demo.repo.UserRepository;
import com.agni.demo.util.ConfigProperties;
import com.agni.demo.util.SMTPService;

@Service
public class UserServiceImpl implements UserService
{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SessionRepository sessionRepository;
	
	PasswordEncoder encoder   = new BCryptPasswordEncoder();
	
	private final String NAME_PARAMETER="NAME_PARAMETER";
	private final String BASE_URL="BASE_URL";
	private String activatelink=ConfigProperties.getPropertyByName("activate-link");
	private String forgotPasswordMaillink=ConfigProperties.getPropertyByName("forgotPasswordMaillink");
	
	private String domain=ConfigProperties.getPropertyByName("domain-name");

	@Override
	public Login login(User name) throws Exception
	{
		// TODO Auto-generated method stub
		Login login=new Login();
		List<UserInterface> userdata = userRepository.findByEmail(name.getEmail());
		Optional<User> nameData = null;
		System.out.println(userdata);
		if (userdata.size() > 0)
		{
			if (userdata.get(0).getIsActive())
			{
				if (checkPassword(name.getPassword(), userdata.get(0).getPassword()))
				{
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

	private boolean checkPassword(String inpassword, String savedpassword2)
	{
		// TODO Auto-generated method stub

		return encoder.matches(inpassword, savedpassword2);
	}

	@Override
	public CreateUserMap saveu(User name) throws Exception
	{

		List<UserInterface> userpartialdata = userRepository.findByEmail(name.getEmail());
		if (userpartialdata.size() > 0)
		{
			if(!userpartialdata.get(0).getIsActive()){
				return sendMail(userRepository.findById(userpartialdata.get(0).getId()).get(),201);
			}
			throw new Exception("User Already Exist");
		} 

		List<String> defaultRole = new ArrayList<>();
		defaultRole.add("MEMBER");
		name.setRole(defaultRole);
		name.setPassword(encoder.encode(name.getPassword()));
		User user = userRepository.save(name);

//		CreateUserMap createusermap = new CreateUserMap();
//		createusermap.setId(user.getId());
//		createusermap.setFirstName(user.getFirstName());
//		createusermap.setLastName(user.getLastName());
//		createusermap.setEmail(user.getEmail());
//
//		String url=activatelink.replace(BASE_URL, domain).replace(NAME_PARAMETER, user.getId().toString());
//		System.out.println(url);
//		SMTPService.send(user.getEmail(), "Account Activation", "Log on to "+url+" to actiavte your account");
//		
		return sendMail(user,200);

	}
	
	public CreateUserMap sendMail(User user,Integer msgCode) throws Exception{
		CreateUserMap createusermap = new CreateUserMap();
		createusermap.setId(user.getId());
		createusermap.setFirstName(user.getFirstName());
		createusermap.setLastName(user.getLastName());
		createusermap.setEmail(user.getEmail());
		createusermap.setMsgCode(msgCode);

		String url=activatelink.replace(BASE_URL, domain).replace(NAME_PARAMETER, user.getId().toString());
		System.out.println(url);
		SMTPService.send(user.getEmail(), "Account Activation", "Log on to "+url+" to actiavte your account");
		
		return createusermap;
		
	}
	
	@Override
	public CreateUserMap activateUser(ObjectId id) throws Exception
	{
		Optional<User> user = userRepository.findById(id);

		if (user.isPresent())
		{

			user.get().setIsActive(true);

			User user1 = userRepository.save(user.get());

			CreateUserMap createusermap = new CreateUserMap();
			createusermap.setId(user1.getId());
			createusermap.setFirstName(user1.getFirstName());
			createusermap.setLastName(user1.getLastName());
			createusermap.setEmail(user1.getEmail());

			return createusermap;
		}

		throw new Exception("User does not exist");
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
	

	@Override
	public CreateUserMap changePassword(User userdetails) throws Exception
	{	
		List<User> user = userRepository.findCompleteByEmail(userdetails.getEmail());
		
		if(user.isEmpty()){
			throw new Exception("User does not exists with emailid - " +userdetails.getEmail());
		}

		if (checkPassword(userdetails.getPassword(), user.get(0).getPassword()) )
		{
			user.get(0).setPassword(userdetails.getNewPassword());
			User user1 = userRepository.save(user.get(0));

			CreateUserMap createusermap = new CreateUserMap();
			createusermap.setId(user1.getId());
			createusermap.setFirstName(user1.getFirstName());
			createusermap.setLastName(user1.getLastName());
			createusermap.setEmail(user1.getEmail());

			return createusermap;
		}

		throw new Exception("Old password did not match");
	}
	
	@Override
	public CreateUserMap changeRole(User userdetails) throws Exception
	{	
		List<User> user = userRepository.findCompleteByEmail(userdetails.getEmail());
		
		if(user.isEmpty()){
			throw new Exception("User does not exists with emailid - " +userdetails.getEmail());
		}
		
			user.get(0).setRole(userdetails.getRole());
			
			User user1 = userRepository.save(user.get(0));

			CreateUserMap createusermap = new CreateUserMap();
			createusermap.setId(user1.getId());
			createusermap.setFirstName(user1.getFirstName());
			createusermap.setLastName(user1.getLastName());
			createusermap.setEmail(user1.getEmail());

			return createusermap;
	}

	
	@Override
	public String forgotPasswordMail(User userdetails) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		List<User> user = userRepository.findCompleteByEmail(userdetails.getEmail());
		if(user.size()>0){
			User usr=user.get(0);
			String str=generateKey(new Date()+ " code for "+usr.getEmail());
			usr.setResetPassword(str);
			String url=forgotPasswordMaillink.replace(BASE_URL, domain).replace(NAME_PARAMETER, str);
			System.out.println(url);
			userRepository.save(usr);
			SMTPService.send(usr.getEmail(), "RESET PASSWORD", "Log on to "+url+" to reset your account");
			return "s";
		}
		return "f";
	}

	@Override
	public String resetPassword(String name) {
		// TODO Auto-generated method stub
		String generatedString="f";
		User user = userRepository.findFirstByResetPassword(name);
		if(user!=null && user.getEmail()!=null){
			generatedString = RandomStringUtils.randomAlphanumeric(10);
			System.out.println(generatedString);
			user.setPassword(encoder.encode(generatedString));
			user.setResetPassword(null);
			userRepository.save(user);
			SMTPService.send(user.getEmail(), "New PASSWORD", "Your new password is "+ generatedString);
			return generatedString;
		}
		
		return generatedString;
	}

	@Override
	public User updateUser(User name) {
		// TODO Auto-generated method stub
		User updateduser=userRepository.findById(name.getId()).get();
		updateduser.setFirstName(name.getFirstName());
		updateduser.setProfileImage(name.getProfileImage());
		updateduser.setLastName(name.getLastName());
		
		
		return userRepository.save(updateduser);
	}
}

