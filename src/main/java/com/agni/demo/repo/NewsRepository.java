package com.agni.demo.repo;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.agni.demo.data.News;

public interface NewsRepository extends MongoRepository<News, ObjectId> {

	
//	Page<News> findByNewsCategory_IdOrderByCreatedDateDesc(ObjectId id,Pageable pageable);

	Page<News> findByNewsCategory_IdAndDeletedOrderByCreatedDateDesc(ObjectId news, boolean b, Pageable pageable);
	
}
