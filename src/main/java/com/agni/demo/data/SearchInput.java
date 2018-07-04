package com.agni.demo.data;

import org.bson.types.ObjectId;

import lombok.Data;

@Data
public class SearchInput {

	private ObjectId id;
	
	private String search;
}
