package com.agni.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agni.demo.data.Person;
import com.agni.demo.repo.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRepository personRepository;

	@Override
	public List<Person> findByLastName(String name) {
		// TODO Auto-generated method stub
		return personRepository.findByLastName(name);
	}

	@Override
	public List<Person> savePerson(List<Person> savePerson) {
		// TODO Auto-generated method stub
		return personRepository.saveAll(savePerson);
	}
	
	
	
	
}
