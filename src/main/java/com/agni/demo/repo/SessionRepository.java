package com.agni.demo.repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.agni.demo.data.Session;

public interface SessionRepository extends MongoRepository<Session, ObjectId> {

	Session findFirstBySessionKey(String auth);
}
