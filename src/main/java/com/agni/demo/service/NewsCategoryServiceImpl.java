package com.agni.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agni.demo.data.CreateUserMap;
import com.agni.demo.data.NewsCategory;
import com.agni.demo.data.User;
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
	
	@Override
	public List<NewsCategory> updateNewsCategory(List<NewsCategory> newsCategory) throws Exception
	{
		Optional<NewsCategory> newscat = newsCategoryRepository.findById(newsCategory.get(0).getId());

		if (newscat.isPresent())
		{

			newscat.get().setNewsCategory(newsCategory.get(0).getNewsCategory());
			newscat.get().setDescription(newsCategory.get(0).getDescription());
			newscat.get().setImage(newsCategory.get(0).getImage());
			newscat.get().setModifiedBy(newsCategory.get(0).getModifiedBy());
			newscat.get().setModifiedDate(newsCategory.get(0).getModifiedDate());

			@SuppressWarnings("unchecked")
			List<NewsCategory> newscat1 = (List<NewsCategory>) newsCategoryRepository.save(newscat.get());

			return (List<NewsCategory>) newscat1;
		}

		throw new Exception("News Category does not exist");
	}
	
}
