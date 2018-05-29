package com.agni.demo.data;

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Template
{
	@Id
	private int id;
	
	private String templateName;
	
	private String template;
	
	private String templateFor;
	
	private String createdBy;
	
	private Date createdDate;
	
	private String modifiedBy;
	
	private Date modifiedDate;
}
