package com.agni.demo.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class NewsCategory
{
	@Id
	private String id;
	
	private String newsCategory;
	
	private String description;
	
	private List<String> image;
	
	private String createdBy;
	
	private Date createdDate=new Date();
	
	private String modifiedBy;
	
	private Date modifiedDate;
}
