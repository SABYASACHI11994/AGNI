package com.agni.demo.service;

import java.util.List;

import com.agni.demo.data.NewsCategory;

public interface NewsCategoryService
{

	List<NewsCategory> saveNewsCategory(List<NewsCategory> newscat);
	
	List<NewsCategory> getNewsCategory();

	NewsCategory updateNewsCategory(NewsCategory newsCategory) throws Exception;
	
	
}
