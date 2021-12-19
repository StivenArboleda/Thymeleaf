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
import com.icesi.edu.Stiven.model.person.Addresstype;
import com.icesi.edu.Stiven.model.person.Businessentity;
import com.icesi.edu.Stiven.model.person.Businessentityaddress;
import com.icesi.edu.Stiven.model.person.Businessentitycontact;
import com.icesi.edu.Stiven.model.person.Contacttype;
import com.icesi.edu.Stiven.model.person.Person;
import com.icesi.edu.Stiven.model.person.Stateprovince;
import com.icesi.edu.Stiven.model.person.UserModel;
import com.icesi.edu.Stiven.model.sales.Customer;
import com.icesi.edu.Stiven.model.sales.Store;

@Component
public class BusinessDelegate {
	
	private RestTemplate template;
	
	private final String baseurl = "http://localhost:8080/api";
	
	public BusinessDelegate() {
		this.template = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
		messageConverters.add(converter);
		this.template.setMessageConverters(messageConverters);
	}
	
	public void setRestTemplate(RestTemplate resttemplate) {
		this.template = resttemplate;
	}

	
	public RestTemplate getRestTemplate() {
		return template;
	}
	
	//=====================================
	//=====================================
	//				USERS
	//=====================================
	//=====================================
	public List<UserModel> showUserList() {
		UserModel[] userarray = template.getForObject(baseurl + "/userRest/", UserModel[].class);
		return Arrays.asList(userarray);
	}
	
	//=====================================
	//=====================================
	//				PERSON
	//=====================================
	//=====================================
	public Person getFindByIdPerson(Integer id) {

		return template.getForObject(baseurl + "/personRest/" + id, Person.class);
	}

	public List<Person> showPersonList() {
		Person[] personarray = template.getForObject(baseurl + "/personRest/", Person[].class);
		return Arrays.asList(personarray);
	}

	public Person addPerson(Person p) {
		HttpEntity<Person> request = new HttpEntity<>(p);
		return template.postForObject(baseurl + "/personRest/", request, Person.class);
	}

	public void deletePerson(Person p) {
		template.delete(baseurl + "/personRest/" + p.getBusinessentityid());
	}

	public void editPerson(Integer id, Person p) {
		HttpEntity<Person> request = new HttpEntity<>(p);

		template.put(baseurl + "/personRest/" + id, request, Person.class);
	}

	public Businessentity saveEntity(Businessentity p) {
		HttpEntity<Businessentity> request = new HttpEntity<>(p);
		return template.postForObject(baseurl + "/businessRest/", request, Businessentity.class);
	}
	
	public List<Customer> getCustomersByPerson(Integer id) {
		Customer[] ca = template.getForObject(baseurl + "/personRest/customers/" + id, Customer[].class);
		return Arrays.asList(ca);
	}
	
	//=====================================
	//=====================================
	//		PROVINCE ADDRES
	//=====================================
	//=====================================
	
	public Stateprovince getFindByIdProvince(Integer id) {

		return template.getForObject(baseurl + "/provinceRest/" + id, Stateprovince.class);
	}
	
	public List<Stateprovince> showProvinceList() {
		Stateprovince[] businessarray = template.getForObject(baseurl + "/provinceRest/", Stateprovince[].class);
		return Arrays.asList(businessarray);
	}
	
	public Stateprovince addProvince(Stateprovince p) {
		HttpEntity<Stateprovince> request = new HttpEntity<>(p);
		return template.postForObject(baseurl + "/provinceRest/", request, Stateprovince.class);
	}

	public void deleteProvince(Stateprovince p) {
		template.delete(baseurl + "/provinceRest/" + p.getStateprovinceid());
	}

	public void editProvince(Integer id, Stateprovince p) {
		HttpEntity<Stateprovince> request = new HttpEntity<>(p);

		template.put(baseurl + "/provinceRest/" + id, request, Stateprovince.class);
	}
	
	//=====================================
	//=====================================
	//		 ADDRESS
	//=====================================
	//=====================================
	
	public Address getFindByIdAddress(Integer id) {

		return template.getForObject(baseurl + "/addressRest/" + id, Address.class);
	}
	
	public List<Address> showAddressList() {
		Address[] businessarray = template.getForObject(baseurl + "/addressRest/", Address[].class);
		return Arrays.asList(businessarray);
	}
	
	public Address addAddress(Address p) {
		HttpEntity<Address> request = new HttpEntity<>(p);
		return template.postForObject(baseurl + "/addressRest/", request, Address.class);
	}

	public void deleteAddress(Address p) {
		template.delete(baseurl + "/addressRest/" + p.getAddressid());
	}

	public void editAddress(Integer id, Address p) {
		HttpEntity<Address> request = new HttpEntity<>(p);

		template.put(baseurl + "/addressRest/" + id, request, Address.class);
	}
	
	//=====================================
	//=====================================
	//		BUSINESS ENTITY ADDRESS
	//=====================================
	//=====================================
	public Businessentityaddress getFindByIdBusiness(Integer id) {

		return template.getForObject(baseurl + "/businessentityRest/" + id, Businessentityaddress.class);
	}

	public List<Businessentityaddress> showBusinessList() {
		Businessentityaddress[] businessarray = template.getForObject(baseurl + "/businessentityRest/",
				Businessentityaddress[].class);
		return Arrays.asList(businessarray);
	}

	public Businessentityaddress addBusiness(Businessentityaddress p) {
		HttpEntity<Businessentityaddress> request = new HttpEntity<>(p);
		return template.postForObject(baseurl + "/businessentityRest/", request, Businessentityaddress.class);
	}

	public void deleteBusiness(Businessentityaddress p) {
		template.delete(baseurl + "/businessentityRest/" + p.getId());
	}

	public void editBusiness(Integer id, Businessentityaddress p) {
		HttpEntity<Businessentityaddress> request = new HttpEntity<>(p);

		template.put(baseurl + "/businessentityRest/" + id, request, Businessentityaddress.class);
	}
	
	//=====================================
	//=====================================
	//				CUSTOMER
	//=====================================
	//=====================================
	public Customer getFindByIdCustomer(Integer id) {

		return template.getForObject(baseurl + "/customerRest/" + id, Customer.class);
	}
	
	public List<Customer> showCustomerList() {
		Customer[] ca = template.getForObject(baseurl + "/customerRest/", Customer[].class);
		return Arrays.asList(ca);
	}
	
	public Customer addCustomer(Customer p) {
		HttpEntity<Customer> request = new HttpEntity<>(p);
		return template.postForObject(baseurl + "/customerRest/", request, Customer.class);
	}

	public void deleteCustomer(Customer p) {
		template.delete(baseurl + "/customerRest/" + p.getCustomerid());
	}

	public void editCustomer(Integer id, Customer p) {
		HttpEntity<Customer> request = new HttpEntity<>(p);

		template.put(baseurl + "/customerRest/" + id, request, Customer.class);
	}
	
	//=====================================
	//=====================================
	//				STORE
	//=====================================
	//=====================================
	
	public Store getFindByIdStore(Integer id) {

		return template.getForObject(baseurl + "/storeRest/" + id, Store.class);
	}
	
	public List<Store> showStoreList() {
		Store[] sa = template.getForObject(baseurl + "/storeRest/", Store[].class);
		return Arrays.asList(sa);
	}
	
	public Store addStore(Store p) {
		HttpEntity<Store> request = new HttpEntity<>(p);
		return template.postForObject(baseurl + "/storeRest/", request, Store.class);
	}

	public void deleteStore(Store p) {
		template.delete(baseurl + "/storeRest/" + p.getBusinessentityid());
	}

	public void editStore(Integer id, Store p) {
		HttpEntity<Store> request = new HttpEntity<>(p);

		template.put(baseurl + "/storeRest/" + id, request, Store.class);
	}
	
	//=====================================
	// =====================================
	// 			AddressType
	// =====================================
	// =====================================

	public Addresstype getFindByIdAType(Integer id) {

		return template.getForObject(baseurl + "/atypeRest/" + id, Addresstype.class);
	}

	public List<Addresstype> showATypeList() {
		Addresstype[] sa = template.getForObject(baseurl + "/atypeRest/", Addresstype[].class);
		return Arrays.asList(sa);
	}

	public Addresstype addAType(Addresstype p) {
		HttpEntity<Addresstype> request = new HttpEntity<>(p);
		return template.postForObject(baseurl + "/atypeRest/", request, Addresstype.class);
	}

	public void deleteAType(Addresstype p) {
		template.delete(baseurl + "/atypeRest/" + p.getAddresstypeid());
	}

	// =====================================
	// =====================================
	// 				Contact
	// =====================================
	// =====================================

	public Businessentitycontact getFindByIdContact(Integer id) {

		return template.getForObject(baseurl + "/atypeRest/" + id, Businessentitycontact.class);
	}

	public List<Businessentitycontact> showContactList() {
		Businessentitycontact[] sa = template.getForObject(baseurl + "/atypeRest/", Businessentitycontact[].class);
		return Arrays.asList(sa);
	}

	public Businessentitycontact addContact(Businessentitycontact p) {
		HttpEntity<Businessentitycontact> request = new HttpEntity<>(p);
		return template.postForObject(baseurl + "/atypeRest/", request, Businessentitycontact.class);
	}

	public void deleteContact(Businessentitycontact p) {
		template.delete(baseurl + "/atypeRest/" + p.getId());
	}
	
	// =====================================
	// =====================================
	// 			ContactType
	// =====================================
	// =====================================

	public Contacttype getFindByIdContactTp(Integer id) {

		return template.getForObject(baseurl + "/atypeRest/" + id, Contacttype.class);
	}

	public List<Contacttype> showContactTpList() {
		Contacttype[] sa = template.getForObject(baseurl + "/atypeRest/", Contacttype[].class);
		return Arrays.asList(sa);
	}

	public Contacttype addContactTp(Contacttype p) {
		HttpEntity<Contacttype> request = new HttpEntity<>(p);
		return template.postForObject(baseurl + "/atypeRest/", request, Contacttype.class);
	}

	public void deleteContactTp(Contacttype p) {
		template.delete(baseurl + "/atypeRest/" + p.getContacttypeid());
	}
}
