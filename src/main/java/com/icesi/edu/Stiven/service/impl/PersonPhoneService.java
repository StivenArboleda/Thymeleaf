package com.icesi.edu.Stiven.service.impl;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.icesi.edu.Stiven.model.person.Personphone;
import com.icesi.edu.Stiven.model.person.PersonphonePK;
import com.icesi.edu.Stiven.repositories.PersonPhoneRepository;

@Service
public class PersonPhoneService {
	
	private PersonPhoneRepository personP;
	
	public PersonPhoneService(PersonPhoneRepository personP) {
		this.personP = personP;
	}
	
	public <S extends Personphone> S save (S personp) {
		if(personp.getId() != null) {
			if(personp.getPhonenumbertype().getPhonenumbertypeid() != null) {
				if(personp.getPerson().getBusinessentityid() != null) {
						personP.save(personp);
						return personp;
				}else {
					throw new NullPointerException("The foreign key of the business entity does not exist");
				}
			}else {
				throw new NullPointerException("The foreign key of the person phone type does not exist");
			}	
		}else {
			throw new NullPointerException("The foreign key of the person phone does not exist");
		}
		
	}
	
	public <S extends Personphone> Iterable<S> saveAll(Iterable<S> pp) {
		for (Personphone personp : pp) {
			save(personp);
		}
		return pp;
	}
	
	public Optional<Personphone> findById(PersonphonePK id) {
		return personP.findById(id);
	}

	public boolean existsById(PersonphonePK id) {
		return personP.existsById(id);
	}
	
	public Iterable<Personphone> findAllById(Iterable<PersonphonePK> pp) {
		return personP.findAllById(pp);
	}
	
	public void editPersonPhone(PersonphonePK id, Timestamp modifieddate) {
		
		if(id.getPhonenumber().length() == 10) {
			Personphone pp = findById(id).get();
			
			pp.setModifieddate(modifieddate);
			
			save(pp);
		}else {
			throw new NumberFormatException("The phone number is not 10 digits");
		}
		
		
	}
	
}
