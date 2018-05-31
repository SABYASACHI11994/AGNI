package com.agni.demo.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;


@Data
public class News
{
	@Id
	private String id;
	
	private String newsHeading;
	
	private String newsSubHeading;
	
	private String newsContent;
	
	private String newsSubContent;
	
//	@Embedded
//	private NewsCategory newsCategory;
	private String author;
	
	private Date validFrom;
	
	private Date validTill;
	
	private List<Image> images;
	
	private List<String> links;
	
	private List<Document> documents;
	
	private List<NewsCategory> newsCategory;
	
	private String createdBy;
	
	private Date createdDate=new Date();
	
	private String modifiedBy;
	
	private Date modifiedDate;
}
