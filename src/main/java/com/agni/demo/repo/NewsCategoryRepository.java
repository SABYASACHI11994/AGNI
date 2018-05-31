package com.agni.demo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.agni.demo.data.NewsCategory;


public interface NewsCategoryRepository extends MongoRepository<NewsCategory, String> {

	

}
