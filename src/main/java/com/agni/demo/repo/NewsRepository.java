package com.agni.demo.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.agni.demo.data.News;

public interface NewsRepository extends MongoRepository<News, String> {

	
	Page<News> findByNewsCategory_NewsCategory(String id,Pageable pageable);
	
}
