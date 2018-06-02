package com.agni.demo.data;

import org.springframework.data.annotation.Transient;

import com.agni.demo.util.OutputMapper;
import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class Login {

	@Expose
	private User user;
	
	@Expose
	private String authKey;
	
	@Transient
	private OutputMapper outputMapper = new OutputMapper();

	@Override
	public String toString() {
		return outputMapper.gson().toJson(this);
	}

}
