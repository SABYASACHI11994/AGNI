package com.agni.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agni.demo.data.News;

public interface NewsService
{

	List<News> saveNews(List<News> newscat);
	
	Page<News> getNews(String news,Pageable pageable);
}
