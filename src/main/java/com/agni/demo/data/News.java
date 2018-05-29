package com.agni.demo.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class News
{
	@Id
	private int id;
	
	private String newsHeading;
	
	private String newsSubHeading;
	
	private String newsContent;
	
	private String newsSubContent;
	
	private String author;
	
	private Date validFrom;
	
	private Date validTill;
	
	List<Image> images;
	
	List<String> links;
	
	List<Document> documents;
	
	List<String> newsCategory;
	
	private String createdBy;
	
	private Date createdDate;
	
	private String modifiedBy;
	
	private Date modifiedDate;
}
