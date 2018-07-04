package com.agni.demo.repo;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.agni.demo.data.News;

public interface NewsRepository extends MongoRepository<News, ObjectId> {

	
//	Page<News> findByNewsCategory_IdOrderByCreatedDateDesc(ObjectId id,Pageable pageable);

	Page<News> findByNewsCategory_IdAndDeletedOrderByCreatedDateDesc(ObjectId news, boolean b, Pageable pageable);

//	@Query("{$or:[{newsHeading:/?1/i},{newsSubHeading:/?1/i}],newsCategory:{$elemMatch:{_id:?0}},deleted:?2}")
//	Page<News> findNewSearch(ObjectId news,String search, boolean b, Pageable pageable);
	
	Page<News> findByNewsCategory_IdAndDeletedAndNewsHeadingIgnoreCaseLikeOrNewsCategory_IdAndDeletedAndNewsSubHeadingIgnoreCaseLikeOrderByCreatedDateDesc(ObjectId news, boolean b,String search,ObjectId news1, boolean b1, String search1,Pageable pageable);
}
