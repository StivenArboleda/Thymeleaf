package com.icesi.edu.Stiven.model.person;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 * The persistent class for the businessentitycontact database table.
 *
 */
@Entity
@NamedQuery(name = "Businessentitycontact.findAll", query = "SELECT b FROM Businessentitycontact b")
public class Businessentitycontact implements Serializable {
	private static final long serialVersionUID = 1L;

	//@EmbeddedId
	//private BusinessentitycontactPK id;
	
	@Id
	@SequenceGenerator(name = "BUSINESSENTITYCONTACT_BUSINESSENTITYCONTACTID_GENERATOR", allocationSize = 1, sequenceName = "BUSINESSENTITYCONTACT_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUSINESSENTITYCONTACT_BUSINESSENTITYCONTACTID_GENERATOR")
	private Integer id;

	private Timestamp modifieddate;

	private Integer rowguid;
	
	private Integer entId;
	
	private Integer contactTpId;
	
	private Integer persId;
	
	// bi-directional many-to-one association to Businessentity
	@ManyToOne
	@JoinColumn(name = "businessentityid")
	private Businessentity businessentity;

	// bi-directional many-to-one association to Contacttype
	@ManyToOne
	@JoinColumn(name = "contacttypeid")
	private Contacttype contacttype;

	// bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name = "personid")
	private Person person;

	public Businessentitycontact() {
	}

	public Businessentity getBusinessentity() {
		return this.businessentity;
	}

	public Contacttype getContacttype() {
		return this.contacttype;
	}

	public Integer getId() {
		return this.id;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public Person getPerson() {
		return this.person;
	}

	public Integer getRowguid() {
		return this.rowguid;
	}

	public void setBusinessentity(Businessentity businessentity) {
		this.businessentity = businessentity;
		setEntId(businessentity.getBusinessentityid());
	}

	public void setContacttype(Contacttype contacttype) {
		this.contacttype = contacttype;
		setContactTpId(contacttype.getContacttypeid());
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setPerson(Person person) {
		this.person = person;
		setPersId(person.getBusinessentityid());
	}

	public void setRowguid(Integer rowguid) {
		this.rowguid = rowguid;
	}

	public Integer getEntId() {
		return entId;
	}

	public void setEntId(Integer entId) {
		this.entId = entId;
	}

	public Integer getContactTpId() {
		return contactTpId;
	}

	public void setContactTpId(Integer contactTpId) {
		this.contactTpId = contactTpId;
	}

	public Integer getPersId() {
		return persId;
	}

	public void setPersId(Integer persId) {
		this.persId = persId;
	}

}