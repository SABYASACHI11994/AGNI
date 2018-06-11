package com.agni.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agni.demo.data.NewsCategory;
import com.agni.demo.service.NewsCategoryService;
import com.agni.demo.util.OutputResponse;

@RestController
public class NewsCategoryController {

	
	@Autowired
	NewsCategoryService newsCategoryService;
	
   
    
    @RequestMapping(value = "/createNewsCategory",method = { RequestMethod.POST },headers = "Authorization", produces = { "application/json" })
    public String createNewsCategory(@RequestBody List<NewsCategory> name) {
 //   	System.out.println(name);
    	OutputResponse response=new OutputResponse();
		try {
			List<NewsCategory> user = newsCategoryService.saveNewsCategory(name);
			response.setResponse(user.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setError(e);
		}
		return response.toString();
//        return ;
    }
    
    @RequestMapping(value = "/getNewsCategory",method = { RequestMethod.GET },headers = "Authorization", produces = { "application/json" })
    public String getNewsCategory() {
//    	System.out.println(name);
    	OutputResponse response=new OutputResponse();
		try {
			 List<NewsCategory> user = newsCategoryService.getNewsCategory();
			response.setResponse(user.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setError(e);
		}
		return response.toString();
//        return newsCategoryService.getNewsCategory();
    }
    
    @RequestMapping(value = "/updateNewsCategory",method = { RequestMethod.POST },headers = "Authorization", produces = { "application/json" })
    public String updateNewsCategory(@RequestBody NewsCategory newsCategory) {
    	OutputResponse response=new OutputResponse();
		try {
			NewsCategory user = newsCategoryService.updateNewsCategory(newsCategory);
			response.setResponse(user.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setError(e);
		}
		return response.toString();
//        return ;
    }
}
