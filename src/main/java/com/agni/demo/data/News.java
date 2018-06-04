package com.agni.demo.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import com.agni.demo.util.OutputMapper;
import com.google.gson.annotations.Expose;

import lombok.Data;


@Data
public class News
{
	@Id
	@Expose
	private String id;
	@Expose
	private String newsHeading;
	@Expose
	private String newsSubHeading;
	@Expose
	private String newsContent;
	@Expose
	private String newsSubContent;
//	@Expose
//	@Embedded
//	private NewsCategory newsCategory;
	@Expose
	private String author;
	@Expose
	private Date validFrom;
	@Expose
	private Date validTill;
	@Expose
	private List<Image> images;
	@Expose
	private List<String> links;
	@Expose
	private List<Document> documents;
	@Expose
	private List<NewsCategory> newsCategory;
	@Expose
	private String createdBy;
	@Expose
	private Date createdDate=new Date();
	@Expose
	private String modifiedBy;
	@Expose
	private Date modifiedDate;
	
//	@Transient
//	private OutputMapper outputMapper = new OutputMapper();
//
//	@Override
//	public String toString() {
//		return outputMapper.gson().toJson(this);
//	}
	
}
