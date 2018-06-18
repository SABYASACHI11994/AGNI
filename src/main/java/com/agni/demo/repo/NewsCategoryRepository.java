package com.agni.demo.repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.agni.demo.data.NewsCategory;


public interface NewsCategoryRepository extends MongoRepository<NewsCategory, ObjectId> {

	

}
