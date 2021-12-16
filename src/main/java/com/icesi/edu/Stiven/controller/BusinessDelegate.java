package com.icesi.edu.Stiven.controller;

import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.icesi.edu.Stiven.model.person.Address;
import com.icesi.edu.Stiven.model.person.Businessentity;
import com.icesi.edu.Stiven.model.person.Person;
import com.icesi.edu.Stiven.model.person.Stateprovince;



@Component
public class BusinessDelegate {
	
	//@Autowired
	private RestTemplate restTemplate;
	
	private final String baseurl = "http://localhost:8080/api";
	
	public BusinessDelegate() {
		this.restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
		messageConverters.add(converter);
		this.restTemplate.setMessageConverters(messageConverters);
	}
	
	public void setRestTemplate(RestTemplate resttemplate) {
		this.restTemplate = resttemplate;
	}

	
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
		
	//PERSON
	
	public Person getFindByIdPerson(Integer id) {

		return restTemplate.getForObject(baseurl + "/personRest/find/" + id, Person.class);
	}
	
	public List<Person> showPersonList() {
		Person[] personarray = restTemplate.getForObject(baseurl + "/personRest/list", Person[].class);
		return Arrays.asList(personarray);
	}
	
	public Person addPerson(Person p) {
		HttpEntity<Person> request = new HttpEntity<>(p);
		return restTemplate.postForObject(baseurl + "/personRest/addperson/", request, Person.class);
	}
	public Businessentity saveEntity(Businessentity p) {
		HttpEntity<Businessentity> request = new HttpEntity<>(p);
		return restTemplate.postForObject(baseurl + "/businessRest/addbusiness/", request, Businessentity.class);
	}
	public void deletePerson(Person p) {
		restTemplate.delete(baseurl + "/personRest/delete/" + p.getBusinessentityid());
	}
}
