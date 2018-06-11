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
	public NewsCategory updateNewsCategory(NewsCategory newsCategory) throws Exception
	{
		Optional<NewsCategory> newscat = newsCategoryRepository.findById(newsCategory.getId());

		if (newscat.isPresent())
		{

			newscat.get().setNewsCategory(newsCategory.getNewsCategory());
			newscat.get().setDescription(newsCategory.getDescription());
			newscat.get().setImage(newsCategory.getImage());
			newscat.get().setModifiedBy(newsCategory.getModifiedBy());
			newscat.get().setModifiedDate(newsCategory.getModifiedDate());

			@SuppressWarnings("unchecked")
			NewsCategory newscat1 =  newsCategoryRepository.save(newscat.get());

			return  newscat1;
		}

		throw new Exception("News Category does not exist");
	}
	
}
