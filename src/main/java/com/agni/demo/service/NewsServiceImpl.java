package com.agni.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.agni.demo.data.News;
import com.agni.demo.repo.NewsRepository;

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
	public Page<News> getNews(String news,Pageable pageable) {
		// TODO Auto-generated method stub
		return newsRepository.findByNewsCategory_NewsCategory(news,pageable);
	}
	
}
