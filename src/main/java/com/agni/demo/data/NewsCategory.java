package com.agni.demo.data;

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class NewsCategory
{
	@Id
	private int id;
	
	private String newsCategory;
	
	private String createdBy;
	
	private Date createdDate;
	
	private String modifiedBy;
	
	private Date modifiedDate;
}
