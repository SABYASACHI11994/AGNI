package com.agni.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agni.demo.data.CreateUserMap;
import com.agni.demo.data.User;
import com.agni.demo.data.UserInterface;
import com.agni.demo.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService
{

	@Autowired
	UserRepository userRepository;

	@Override
	public User login(User name) throws Exception
	{
		// TODO Auto-generated method stub
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
				}
			}

		}
		System.out.println(nameData);
		return name;
	}

	private boolean checkPassword(String password, String password2)
	{
		// TODO Auto-generated method stub

		return password.equals(password2);
	}

	@Override
	public CreateUserMap saveu(User name) throws Exception
	{

		List<UserInterface> userpartialdata = userRepository.findByEmail(name.getEmail());
		if (userpartialdata.size() > 0)
		{
			throw new Exception("User Already Exist");
		}
		User user = userRepository.save(name);

		CreateUserMap createusermap = new CreateUserMap();
		createusermap.setId(user.getId());
		createusermap.setFirstName(user.getFirstName());
		createusermap.setLastName(user.getLastName());
		createusermap.setEmail(user.getEmail());
		createusermap.setIsActive(user.getIsActive());

		return createusermap;

	}

	@Override
	public String activateUser(User name)
	{
		List<UserInterface> userpartialdata = userRepository.findByEmail(name.getEmail());

		if (userpartialdata.isEmpty())
		{

			return "User Not Found";

		} else
			// userpartialdata.get(0).getisActive()
			// TODO Auto-generated method stub
			return null;
	}

}
