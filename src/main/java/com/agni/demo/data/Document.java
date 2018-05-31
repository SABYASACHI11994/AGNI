package com.agni.demo.data;

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Document
{
	@Id
	private int id;
	
	private String documentName;
	
	private String documentPath;
	
	private String documentData;
	
	private String documentType;
	
	private String createdBy;
	
	private Date createdDate;
	
	private String modifiedBy;
	
	private Date modifiedDate;
}
