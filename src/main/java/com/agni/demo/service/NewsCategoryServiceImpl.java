package com.agni.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agni.demo.data.CreateUserMap;
import com.agni.demo.data.News;
import com.agni.demo.data.NewsCategory;
import com.agni.demo.data.User;
import com.agni.demo.repo.NewsCategoryRepository;
import com.agni.demo.util.ConfigProperties;

@Service
public class NewsCategoryServiceImpl implements NewsCategoryService
{
	private String forgotPasswordMaillink=ConfigProperties.getPropertyByName("forgotPasswordMaillink");

	 @PostConstruct
	    public void init() {
		 List<NewsCategory> cat=newsCategoryRepository.findByNewsCategoryIgnoreCase("Agenda");
		 if(cat.size()==0){
			 NewsCategory c=new NewsCategory();
			 c.setNewsCategory("Agenda");
			 c.setDescription("Agenda");
			 ArrayList<String> x =new ArrayList<String>();
			 x.add(ConfigProperties.getPropertyByName("agenda-image"));		 
			c.setImage(x);
			 newsCategoryRepository.save(c);
			 System.out.println("no agenda");
		 }else{
			 System.out.println("found agenda");
		 }
	    }
	 
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
		return newsCategoryRepository.findByDeleted(false);
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

	@Override
	public NewsCategory deleteNewsCategory(NewsCategory name) {
		// TODO Auto-generated method stub
		NewsCategory news=newsCategoryRepository.findById(name.getId()).get();
		news.setDeleted(true);
		return newsCategoryRepository.save(news);
	}
	
}
