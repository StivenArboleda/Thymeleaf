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
import javax.validation.constraints.Size;

/**
 * The persistent class for the personphone database table.
 *
 */
@Entity
@NamedQuery(name = "Personphone.findAll", query = "SELECT p FROM Personphone p")
public class Personphone implements Serializable {
	private static final long serialVersionUID = 1L;

	//@EmbeddedId
	//private PersonphonePK id;
	@Id
	@SequenceGenerator(name = "PERSONPHONE_PERSONPHONE_GENERATOR", allocationSize = 1, sequenceName = "PERSONPHONE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSONPHONE_PERSONPHONEID_GENERATOR")
	private Integer id;
	private Timestamp modifieddate;

	// bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name = "businessentityid", insertable = false, updatable = false)
	private Person person;

	// bi-directional many-to-one association to Phonenumbertype
	@ManyToOne
	@JoinColumn(name = "phonenumbertypeid", insertable = false, updatable = false)
	private Phonenumbertype phonenumbertype;

	@Size(min = 10, max = 10)
	private String phone;
	
	public Personphone() {
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

	public Phonenumbertype getPhonenumbertype() {
		return this.phonenumbertype;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setPhonenumbertype(Phonenumbertype phonenumbertype) {
		this.phonenumbertype = phonenumbertype;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


}