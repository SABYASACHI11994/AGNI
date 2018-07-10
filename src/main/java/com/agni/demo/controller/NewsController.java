package com.agni.demo.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.agni.demo.data.News;
import com.agni.demo.data.SearchInput;
import com.agni.demo.service.FileStorageService;
import com.agni.demo.service.NewsService;
import com.agni.demo.service.PersonService;
import com.agni.demo.util.OutputMapper;
import com.agni.demo.util.OutputResponse;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;

@RestController
public class NewsController {

	// private static final String template = "Hello, %s!";
	// private final AtomicLong counter = new AtomicLong();

	@Autowired
	PersonService personService;

	@Autowired
	NewsService newsService;

	@Autowired
	GridFsOperations gridOperations;
	
	@Autowired
	FileStorageService fileStorageService;
	


	private OutputMapper outputMapper = new OutputMapper();

	// @RequestMapping(value = "/findByLastName",method = { RequestMethod.GET })
	// public List<Person> findByLastName(@RequestParam(value="name",
	// defaultValue="World") String name) {
	// return personService.findByLastName(name);
	// }
	//
	// @RequestMapping(value = "/createPerson",method = { RequestMethod.POST })
	// public List<Person> createPerson(@RequestBody List<Person> name) {
	// System.out.println(name);
	// return personService.savePerson(name);
	// }
	@CrossOrigin()
	@RequestMapping(value = "/createNews", method = { RequestMethod.POST }, headers = "Authorization", produces = {
			"application/json" })
	public String createNews(@RequestBody List<News> name) {
		System.out.println(name);
		OutputResponse response = new OutputResponse();
		try {
			List<News> news = newsService.saveNews(name);
			response.setResponse(outputMapper.gson().toJson(news));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setError(e);
		}
		return response.toString();
		// return ;
	}

	@CrossOrigin()
	@RequestMapping(value = "/deleteNews", method = { RequestMethod.POST }, headers = "Authorization", produces = {
			"application/json" })
	public String deleteNews(@RequestBody News name) {
		System.out.println(name);
		OutputResponse response = new OutputResponse();
		try {
			News news = newsService.deleteNews(name);
			response.setResponse(outputMapper.gson().toJson(news));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setError(e);
		}
		return response.toString();
		// return ;
	}

	@CrossOrigin()
	@RequestMapping(value = "/updateNews", method = { RequestMethod.POST }, headers = "Authorization", produces = {
			"application/json" })
	public String updateNews(@RequestBody News name) {
		System.out.println(name);
		OutputResponse response = new OutputResponse();
		try {
			News news = newsService.updateNews(name);
			response.setResponse(outputMapper.gson().toJson(news));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setError(e);
		}
		return response.toString();
		// return ;
	}

	@CrossOrigin()
	@RequestMapping(value = "/getLatestNews/{name}/{page}/{size}", method = {
			RequestMethod.GET }, headers = "Authorization", produces = { "application/json" })
	public Page<News> getLatestNews(@PathVariable("name") ObjectId name, @PathVariable("page") Integer page,
			@PathVariable("size") Integer size) {
		// System.out.println(name);
		// OutputResponse response=new OutputResponse();
		// Page<News> news=new
		// try {
		Pageable pageable = new PageRequest(page, size);
		Page<News> news = newsService.getNews(name, pageable);
		return news;
		//// response.setResponse(news.toString());
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// response.setError(e);
		// }
		// return news;
		// return ;
	}

	@CrossOrigin()
	@RequestMapping(value = "/getLatestNewsSearch/{page}/{size}", method = {
			RequestMethod.POST }, headers = "Authorization", produces = { "application/json" })
	public Page<News> getLatestNewsSearch(@RequestBody SearchInput search, @PathVariable("page") Integer page,
			@PathVariable("size") Integer size) {
		// System.out.println(name);
		// OutputResponse response=new OutputResponse();
		// Page<News> news=new
		// try {
		Pageable pageable = new PageRequest(page, size);
		Page<News> news = newsService.getNewsSearch(search.getId(), search.getSearch(), pageable);
		return news;
		//// response.setResponse(news.toString());
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// response.setError(e);
		// }
		// return news;
		// return ;
	}

	@CrossOrigin()
	@RequestMapping(value = "/uploadFile", method = { RequestMethod.POST }, produces = { "application/json" })
	public String uploadFile(@RequestParam("file") MultipartFile file) throws FileNotFoundException {
		// Define metaData
		DBObject metaData = new BasicDBObject();
		metaData.put("organization", "agni");

		/**
		 * 1. save an image file to MongoDB
		 */

		// Get input file
		// InputStream iamgeStream = new
		// FileInputStream("C:\\Users\\sa356897\\Pictures\\Screenshots\\Screenshot
		// (2).png");
		//
		// metaData.put("type", "image");

		// Store file to MongoDB
		String imageFileId = "";
		try {
			// ObjectId xx=gridOperations.store(file.getInputStream(),
			// file.getName(), file.getContentType(), metaData);
			ObjectId iii = gridOperations.store(file.getInputStream(), file.getName(), file.getContentType(), metaData);
//			ObjectId iii = mongoTemplate.sa store(file.getInputStream(), file.getName(), file.getContentType(), metaData);
			imageFileId=iii.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ImageFileId = " + imageFileId);

		return "{\"id\":\"" + imageFileId + "\"}";
	}

//	@Autowired 
//	MongoTemplate mongoTemplate;
	
//	@Autowired
//	GridFS gridFs;

//	
//	@Autowired
//	MongoDbFactory mongoDbFactory;
//	private GridFSBucket getGridFs() {
//
//	    MongoDatabase db = mongoDbFactory.getDb();
//	    return GridFSBuckets.create(db);
//	}
	
	@CrossOrigin()
	@RequestMapping(value = "/downloadFile/{name}", method = { RequestMethod.GET })
	public ResponseEntity<Resource> downloadFile(@PathVariable("name") ObjectId name,HttpServletResponse response,HttpServletRequest request) throws FileNotFoundException {
		// Define metaData

		// gridOperations.findOne(arg0);
		GridFSFile file = gridOperations.findOne(Query.query(Criteria.where("_id").is(name)));
		GridFsResource fsres=gridOperations.getResource(file.getFilename());
//		fsres.
//		GridFSDBFile outputImageFile = gridFs.findOne(Query.query(Criteria.where("_id").is(name)));
//		outputImageFile.w
//		GridFSDBFile filedetail=mongoTemplate.findOne(Query.query(Criteria.where("_id").is(name)),GridFSDBFile.class);
////		GridFSDBFile ss=
//		InputStream s= filedetail.getInputStream();
////		
////		file.get
////		file.
//		Resource resource;
//		try {
//			resource = fileStorageService.loadFileAsResource(name.toString());
//			 String contentType = null;
////		        try {
//////		            contentType = request.getServletContext().getMimeType(file.getFile().getAbsolutePath());
////		        } catch (IOException ex) {
//////		            logger.info("Could not determine file type.");
////		        }
//
//		        // Fallback to the default content type if type could not be determined
//		        if(contentType == null) {
//		            contentType = "application/octet-stream";
//		        }
//
		        try {
					return ResponseEntity.ok()
					        .contentType(MediaType.parseMediaType(file.getMetadata().getString("_contentType")))
					        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
					        .body(new InputStreamResource( fsres.getInputStream()));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		        return (ResponseEntity<Resource>) ResponseEntity.badRequest();
//	    return file.toString();
	}
}