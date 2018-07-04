package com.agni.demo.service;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.agni.demo.data.News;
import com.agni.demo.repo.NewsRepository;
import com.google.gson.annotations.Expose;

@Service
public class NewsServiceImpl implements NewsService
{

	@Autowired
	NewsRepository newsRepository;
	
	@Override
	public List<News> saveNews(List<News> newscat)
	{
		// TODO Auto-generated method stub
		return newsRepository.saveAll(newscat);
	}

	@Override
	public Page<News> getNews(ObjectId news,Pageable pageable) {
		// TODO Auto-generated method stub
		return newsRepository.findByNewsCategory_IdAndDeletedOrderByCreatedDateDesc(news,false,pageable);
	}

	@Override
	public News deleteNews(News name) {
		// TODO Auto-generated method stub
		News news=newsRepository.findById(name.getId()).get();
		news.setDeleted(true);
		return newsRepository.save(news);
	}

	@Override
	public News updateNews(News name) {
		// TODO Auto-generated method stub
		News news=newsRepository.findById(name.getId()).get();
//		news.setDeleted(true);
		news.setModifiedBy(name.getModifiedBy());
		news.setModifiedDate(new Date());
		news.setNewsHeading(name.getNewsHeading());
		news.setNewsSubHeading(name.getNewsSubHeading());
		news.setNewsContent(name.getNewsContent());
		news.setNewsSubContent(name.getNewsSubContent());
		
				
		return newsRepository.save(news);
	}

	@Override
	public Page<News> getNewsSearch(ObjectId news,String search, Pageable pageable) {
		// TODO Auto-generated method stub
		return newsRepository.findByNewsCategory_IdAndDeletedAndNewsHeadingIgnoreCaseLikeOrNewsCategory_IdAndDeletedAndNewsSubHeadingIgnoreCaseLikeOrderByCreatedDateDesc(news,false,search,news,false,search,pageable);
	}
	
}
