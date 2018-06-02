package com.agni.demo.repo;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.agni.demo.data.User;
import com.agni.demo.data.UserInterface;


public interface UserRepository extends MongoRepository<User, ObjectId> {

	List<UserInterface> findByEmail(String name);

//	User saveAll(Optional<User> user);
	
//	List<UserInterface> findById(?ObjectId id);
 

}
