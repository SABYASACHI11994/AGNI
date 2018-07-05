package com.agni.demo.repo;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.agni.demo.data.NewsCategory;


public interface NewsCategoryRepository extends MongoRepository<NewsCategory, ObjectId> {

	List<NewsCategory> findByDeleted(boolean b);

	List<NewsCategory> findByNewsCategoryIgnoreCase(String string);

	

}
