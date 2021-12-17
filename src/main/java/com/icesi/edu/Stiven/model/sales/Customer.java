package com.icesi.edu.Stiven.model.sales;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.icesi.edu.Stiven.model.person.Person;

/**
 * The persistent class for the customer database table.
 *
 */
@Entity
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "CUSTOMER_CUSTOMERID_GENERATOR", allocationSize = 1, sequenceName = "CUSTOMER_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_CUSTOMERID_GENERATOR")
	private Integer customerid;

	private Timestamp modifieddate;

	private Integer personid;

	private Integer rowguid;
	
	private Integer storeid1;
	
	private String storeN;
	
	// bi-directional many-to-one association to Salesterritory
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "territoryid")
	private Salesterritory salesterritory;

	// bi-directional many-to-one association to Store
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "storeid")
	private Store store;
	
	// bi-directional many-to-one association to Store
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "personid1")
	private Person person;

	// bi-directional many-to-one association to Salesorderheader
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Salesorderheader> salesorderheaders;

	public Customer() {
	}

	public Salesorderheader addSalesorderheader(Salesorderheader salesorderheader) {
		getSalesorderheaders().add(salesorderheader);
		salesorderheader.setCustomer(this);

		return salesorderheader;
	}

	public Integer getCustomerid() {
		return this.customerid;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public Integer getPersonid() {
		return this.personid;
	}

	public Integer getRowguid() {
		return this.rowguid;
	}

	public List<Salesorderheader> getSalesorderheaders() {
		return this.salesorderheaders;
	}

	public Salesterritory getSalesterritory() {
		return this.salesterritory;
	}

	public Store getStore() {
		return this.store;
	}
	
	public Person getPerson() {
		return this.person;
	}

	public Salesorderheader removeSalesorderheader(Salesorderheader salesorderheader) {
		getSalesorderheaders().remove(salesorderheader);
		salesorderheader.setCustomer(null);

		return salesorderheader;
	}

	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setPersonid(Integer personid) {
		this.personid = personid;
	}

	public void setRowguid(Integer rowguid) {
		this.rowguid = rowguid;
	}

	public void setSalesorderheaders(List<Salesorderheader> salesorderheaders) {
		this.salesorderheaders = salesorderheaders;
	}

	public void setSalesterritory(Salesterritory salesterritory) {
		this.salesterritory = salesterritory;
	}

	public void setStore(Store store) {
		this.store = store;
		storeid1 = store.getBusinessentityid();
		storeN = store.getName();
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}

	public Integer getStoreid1() {
		return storeid1;
	}

	public void setStoreid1(Integer storeid1) {
		this.storeid1 = storeid1;
	}

	public String getStoreN() {
		return storeN;
	}

	public void setStoreN(String storeN) {
		this.storeN = storeN;
	}

}