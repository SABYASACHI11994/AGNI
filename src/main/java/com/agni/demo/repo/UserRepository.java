package com.agni.demo.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.agni.demo.data.User;
import com.agni.demo.data.UserInterface;


public interface UserRepository extends MongoRepository<User, String> {

	List<UserInterface> findByEmail(String name);
	

}
