package com.icesi.edu.Stiven.model.person;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 * The persistent class for the businessentityaddress database table.
 *
 */
@Entity
@NamedQuery(name = "Businessentityaddress.findAll", query = "SELECT b FROM Businessentityaddress b")
public class Businessentityaddress implements Serializable {
	private static final long serialVersionUID = 1L;

	//@EmbeddedId
	//private BusinessentityaddressPK id;
	
	@Id
	@SequenceGenerator(name = "BUSINESSENTITYADDRESS_BUSINESSENTITYADDRESSID_GENERATOR", allocationSize = 1, sequenceName = "BUSINESSENTITYADDRESS_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUSINESSENTITYADDRESS_BUSINESSENTITYADDRESSID_GENERATOR")
	private Integer id;
	
	@Column(insertable=false, updatable=false)
	private Integer businessentityid;

	@Column(insertable=false, updatable=false)
	private Integer addressid;

	@Column(insertable=false, updatable=false)
	private Integer addresstypeid;
		
	private Timestamp modifieddate;

	private Integer rowguid;

	// bi-directional many-to-one association to Address
	@ManyToOne
	@JoinColumn(name = "addressid", insertable = false, updatable = false)
	private Address address;

	// bi-directional many-to-one association to Addresstype
	@ManyToOne
	@JoinColumn(name = "addresstypeid", insertable = false, updatable = false)
	private Addresstype addresstype;

	// bi-directional many-to-one association to Businessentity
	@ManyToOne
	@JoinColumn(name = "businessentityid", insertable = false, updatable = false)
	private Businessentity businessentity;

	public Businessentityaddress() {
	}

	public Address getAddress() {
		return this.address;
	}

	public Addresstype getAddresstype() {
		return this.addresstype;
	}

	public Businessentity getBusinessentity() {
		return this.businessentity;
	}

	public Integer getId() {
		return this.id;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public Integer getRowguid() {
		return this.rowguid;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setAddresstype(Addresstype addresstype) {
		this.addresstype = addresstype;
	}

	public void setBusinessentity(Businessentity businessentity) {
		this.businessentity = businessentity;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setRowguid(Integer rowguid) {
		this.rowguid = rowguid;
	}
	
	public Integer getBusinessentityid() {
		return this.businessentityid;
	}
	public void setBusinessentityid(Integer businessentityid) {
		this.businessentityid = businessentityid;
	}
	public Integer getAddressid() {
		return this.addressid;
	}
	public void setAddressid(Integer addressid) {
		this.addressid = addressid;
	}
	public Integer getAddresstypeid() {
		return this.addresstypeid;
	}
	public void setAddresstypeid(Integer addresstypeid) {
		this.addresstypeid = addresstypeid;
	}

}