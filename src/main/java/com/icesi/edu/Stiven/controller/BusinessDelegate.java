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
import com.icesi.edu.Stiven.model.person.Businessentityaddress;
import com.icesi.edu.Stiven.model.person.Person;
import com.icesi.edu.Stiven.model.person.Stateprovince;
import com.icesi.edu.Stiven.model.person.UserModel;
import com.icesi.edu.Stiven.model.sales.Customer;



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
	
	//=====================================
	//=====================================
	//				USERS
	//=====================================
	//=====================================
	public List<UserModel> showUserList() {
		UserModel[] userarray = restTemplate.getForObject(baseurl + "/userRest/list", UserModel[].class);
		return Arrays.asList(userarray);
	}
	
	//=====================================
	//=====================================
	//				PERSON
	//=====================================
	//=====================================
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

	public void deletePerson(Person p) {
		restTemplate.delete(baseurl + "/personRest/delete/" + p.getBusinessentityid());
	}

	public void editPerson(Integer id, Person p) {
		HttpEntity<Person> request = new HttpEntity<>(p);

		restTemplate.put(baseurl + "/personRest/edit/" + id, request, Person.class);
	}
	
	public Businessentity saveEntity(Businessentity p) {
		HttpEntity<Businessentity> request = new HttpEntity<>(p);
		return restTemplate.postForObject(baseurl + "/businessRest/addbusiness/", request, Businessentity.class);
	}
	
	public List<Customer> getCustomersByPerson(Integer id) {
		Customer[] ca = restTemplate.getForObject(baseurl + "/personRest/listCustomers/" + id, Customer[].class);
		return Arrays.asList(ca);
	}
	
	//=====================================
	//=====================================
	//		PROVINCE ADDRES
	//=====================================
	//=====================================
	
	public Stateprovince getFindByIdProvince(Integer id) {

		return restTemplate.getForObject(baseurl + "/provinceRest/find/" + id, Stateprovince.class);
	}
	
	public List<Stateprovince> showProvinceList() {
		Stateprovince[] businessarray = restTemplate.getForObject(baseurl + "/provinceRest/list", Stateprovince[].class);
		return Arrays.asList(businessarray);
	}
	
	public Stateprovince addProvince(Stateprovince p) {
		HttpEntity<Stateprovince> request = new HttpEntity<>(p);
		return restTemplate.postForObject(baseurl + "/provinceRest/addProvince/", request, Stateprovince.class);
	}

	public void deleteProvince(Stateprovince p) {
		restTemplate.delete(baseurl + "/provinceRest/delete/" + p.getStateprovinceid());
	}

	public void editProvince(Integer id, Stateprovince p) {
		HttpEntity<Stateprovince> request = new HttpEntity<>(p);

		restTemplate.put(baseurl + "/provinceRest/edit/" + id, request, Stateprovince.class);
	}
	
	//=====================================
	//=====================================
	//		 ADDRESS
	//=====================================
	//=====================================
	
	public Address getFindByIdAddress(Integer id) {

		return restTemplate.getForObject(baseurl + "/addressRest/find/" + id, Address.class);
	}
	
	public List<Address> showAddressList() {
		Address[] businessarray = restTemplate.getForObject(baseurl + "/addressRest/list", Address[].class);
		return Arrays.asList(businessarray);
	}
	
	public Address addAddress(Address p) {
		HttpEntity<Address> request = new HttpEntity<>(p);
		return restTemplate.postForObject(baseurl + "/addressRest/add/", request, Address.class);
	}

	public void deleteAddress(Address p) {
		restTemplate.delete(baseurl + "/addressRest/delete/" + p.getAddressid());
	}

	public void editAddress(Integer id, Address p) {
		HttpEntity<Address> request = new HttpEntity<>(p);

		restTemplate.put(baseurl + "/addressRest/edit/" + id, request, Address.class);
	}
	
	//=====================================
	//=====================================
	//		BUSINESS ENTITY ADDRESS
	//=====================================
	//=====================================
public Businessentityaddress getFindByIdBusiness(Integer id) {

	return restTemplate.getForObject(baseurl + "/businessentityRest/find/" + id, Businessentityaddress.class);
}

public List<Businessentityaddress> showBusinessList() {
	Businessentityaddress[] businessarray = restTemplate.getForObject(baseurl + "/businessentityRest/list", Businessentityaddress[].class);
	return Arrays.asList(businessarray);
}

public Businessentityaddress addBusiness(Businessentityaddress p) {
	HttpEntity<Businessentityaddress> request = new HttpEntity<>(p);
	return restTemplate.postForObject(baseurl + "/businessentityRest/add/", request, Businessentityaddress.class);
}

public void deleteBusiness(Businessentityaddress p) {
	restTemplate.delete(baseurl + "/businessentityRest/delete/" + p.getId());
}

public void editBusiness(Integer id, Businessentityaddress p) {
	HttpEntity<Businessentityaddress> request = new HttpEntity<>(p);

	restTemplate.put(baseurl + "/businessentityRest/edit/" + id, request, Businessentityaddress.class);
}
}
