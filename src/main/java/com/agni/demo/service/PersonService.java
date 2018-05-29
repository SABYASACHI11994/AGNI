package com.agni.demo.service;

import java.util.List;

import com.agni.demo.data.Person;

public interface PersonService {

	List<Person> findByLastName(String name);
	
	List<Person>  savePerson(List<Person> savePerson);
}
