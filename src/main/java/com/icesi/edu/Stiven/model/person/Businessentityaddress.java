package com.icesi.edu.Stiven.model.person;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
			
	private Timestamp modifieddate;

	private Integer rowguid;
	
	private Integer addId;

	private Integer addTpId;

	private Integer entId;

	// bi-directional many-to-one association to Address
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "addressid")
	private Address address;

	// bi-directional many-to-one association to Addresstype
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "addresstypeid")
	private Addresstype addresstype;

	// bi-directional many-to-one association to Businessentity
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "businessentityid")
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
		addId = address.getAddressid();
	}

	public void setAddresstype(Addresstype addresstype) {
		this.addresstype = addresstype;
		addTpId = addresstype.getAddresstypeid();
	}

	public void setBusinessentity(Businessentity businessentity) {
		this.businessentity = businessentity;
		entId = businessentity.getBusinessentityid();
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

	public Integer getAddId() {
		return addId;
	}

	public void setAddId(Integer addId) {
		this.addId = addId;
	}

	public Integer getAddTpId() {
		return addTpId;
	}

	public void setAddTpId(Integer addTpId) {
		this.addTpId = addTpId;
	}

	public Integer getEntId() {
		return entId;
	}

	public void setEntId(Integer entId) {
		this.entId = entId;
	}
	


}