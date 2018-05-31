package com.agni.demo.data;

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Image
{
	@Id
	private int id;
	
	private String imageName;
	
	private String imagePath;
	
	private String imageData;
	
	private String imageType;
	
	private String createdBy;
	
	private Date createdDate;
	
	private String modifiedBy;
	
	private Date modifiedDate;
}
