package com.agni.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agni.demo.data.User;
import com.agni.demo.data.UserInterface;
import com.agni.demo.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User login(User name) throws Exception {
		// TODO Auto-generated method stub
		List<UserInterface> userdata = userRepository.findByEmail(name.getEmail());
		Optional<User> nameData;
		System.out.println(userdata);
		if (userdata.size() > 0) {
			if (userdata.get(0).getIsActive()) {
				if (checkPassword(name.getPassword(), userdata.get(0).getPassword())) {
					nameData = userRepository.findById(userdata.get(0).getId());
				}
			}

		}
		if(true){
			throw new Exception();
		}
		
		return name;
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

}
