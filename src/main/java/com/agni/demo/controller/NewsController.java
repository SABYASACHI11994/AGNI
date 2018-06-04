package com.agni.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agni.demo.data.News;
import com.agni.demo.service.NewsService;
import com.agni.demo.service.PersonService;
import com.agni.demo.util.OutputResponse;

@RestController
public class NewsController {

//    private static final String template = "Hello, %s!";
//    private final AtomicLong counter = new AtomicLong();

	@Autowired
	PersonService personService;
	
	@Autowired
	NewsService newsService;
	
	
//    @RequestMapping(value = "/findByLastName",method = { RequestMethod.GET })
//    public List<Person> findByLastName(@RequestParam(value="name", defaultValue="World") String name) {
//        return personService.findByLastName(name);
//    }
//    
//    @RequestMapping(value = "/createPerson",method = { RequestMethod.POST })
//    public List<Person> createPerson(@RequestBody List<Person> name) {
//    	System.out.println(name);
//        return personService.savePerson(name);
//    }
    
    @RequestMapping(value = "/createNews",method = { RequestMethod.POST })
    public String createNews(@RequestBody List<News> name) {
    	System.out.println(name);
    	OutputResponse response=new OutputResponse();
		try {
			List<News> news=newsService.saveNews(name);
			response.setResponse(news.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setError(e);
		}
		return response.toString();
//        return ;
    }
    
    @RequestMapping(value = "/getLatestNews/{name}/{page}/{size}",method = { RequestMethod.GET })
    public Page<News> getLatestNews(@PathVariable("name") String name,@PathVariable("page") Integer page,@PathVariable("size")Integer size) {
//    	System.out.println(name);
//    	OutputResponse response=new OutputResponse();
//    	Page<News> news=new 
//		try {
    	Pageable pageable=new PageRequest(page, size);
			return newsService.getNews(name,pageable);
////			response.setResponse(news.toString());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			response.setError(e);
//		}
//		return news;
//        return ;
    }
}