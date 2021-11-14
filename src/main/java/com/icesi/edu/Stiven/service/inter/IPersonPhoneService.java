package com.icesi.edu.Stiven.service.inter;

import java.sql.Timestamp;
import java.util.Optional;

import com.icesi.edu.Stiven.model.person.Personphone;
import com.icesi.edu.Stiven.model.person.PersonphonePK;

public interface IPersonPhoneService {
	
	public <S extends Personphone> S save (S personp);
		
	public <S extends Personphone> Iterable<S> saveAll(Iterable<S> pp);
	
	public Optional<Personphone> findById(PersonphonePK id);

	public boolean existsById(PersonphonePK id);
	
	public Iterable<Personphone> findAllById(Iterable<PersonphonePK> pp);
	
	public void editPersonPhone(PersonphonePK id, Timestamp modifieddate);
	
}	
