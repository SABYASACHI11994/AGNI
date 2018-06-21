package com.agni.demo.data;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import com.agni.demo.util.OutputMapper;
import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class NewsCategory
{
	@Id
	@Expose
	private ObjectId id;
	
	@Expose
	private String newsCategory;
	
	@Expose
	private String description;
	@Expose
	private List<String> image;
	@Expose
	private String createdBy;
	@Expose
	private Date createdDate=new Date();
	@Expose
	private String modifiedBy;
	@Expose
	private Date modifiedDate;
	
	@Expose
	private Boolean deleted=false;

//	@Transient
//	private OutputMapper outputMapper = new OutputMapper();
//
//	@Override
//	public String toString() {
//		return outputMapper.gson().toJson(this);
//	}
}
