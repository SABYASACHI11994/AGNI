package com.agni.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agni.demo.data.NewsCategory;
import com.agni.demo.service.NewsCategoryService;

@RestController
public class NewsCategoryController {

	
	@Autowired
	NewsCategoryService newsCategoryService;
	
   
    
    @RequestMapping(value = "/createNewsCategory",method = { RequestMethod.POST },headers = "Authorization", produces = { "application/json" })
    public List<NewsCategory> createNewsCategory(@RequestBody List<NewsCategory> name) {
    	System.out.println(name);
        return newsCategoryService.saveNewsCategory(name);
    }
    
    @RequestMapping(value = "/getNewsCategory",method = { RequestMethod.GET },headers = "Authorization", produces = { "application/json" })
    public List<NewsCategory> getNewsCategory() {
//    	System.out.println(name);
        return newsCategoryService.getNewsCategory();
    }
}
