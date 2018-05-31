package com.agni.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agni.demo.data.NewsCategory;
import com.agni.demo.repo.NewsCategoryRepository;

@Service
public class NewsCategoryServiceImpl implements NewsCategoryService
{

	@Autowired
	NewsCategoryRepository newsCategoryRepository;
	
	@Override
	public List<NewsCategory> saveNewsCategory(List<NewsCategory> newscat)
	{
		// TODO Auto-generated method stub
		return newsCategoryRepository.saveAll(newscat);
	}

	@Override
	public List<NewsCategory> getNewsCategory() {
		// TODO Auto-generated method stub
		return newsCategoryRepository.findAll();
	}
	
}
