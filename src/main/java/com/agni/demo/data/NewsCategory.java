package com.agni.demo.data;

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class NewsCategory
{
	@Id
	private String id;
	
	private String newsCategory;
	
	private String createdBy;
	
	private Date createdDate=new Date();
	
	private String modifiedBy;
	
	private Date modifiedDate;
}
