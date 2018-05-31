package com.agni.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agni.demo.data.News;
import com.agni.demo.data.Person;
import com.agni.demo.service.NewsService;
import com.agni.demo.service.PersonService;

@RestController
public class NewsController {

//    private static final String template = "Hello, %s!";
//    private final AtomicLong counter = new AtomicLong();

	@Autowired
	PersonService personService;
	
	@Autowired
	NewsService newsService;
	
	
    @RequestMapping(value = "/findByLastName",method = { RequestMethod.GET })
    public List<Person> findByLastName(@RequestParam(value="name", defaultValue="World") String name) {
        return personService.findByLastName(name);
    }
    
    @RequestMapping(value = "/createPerson",method = { RequestMethod.POST })
    public List<Person> createPerson(@RequestBody List<Person> name) {
    	System.out.println(name);
        return personService.savePerson(name);
    }
    
    @RequestMapping(value = "/createNews",method = { RequestMethod.POST })
    public List<News> createNews(@RequestBody List<News> name) {
    	System.out.println(name);
        return newsService.saveNews(name);
    }
    
    @RequestMapping(value = "/getLatestNews",method = { RequestMethod.POST })
    public Page<News> getLatestNews(@RequestParam String name,Pageable pageable) {
    	System.out.println(name);
    	
        return newsService.getNews(name,pageable);
    }
}