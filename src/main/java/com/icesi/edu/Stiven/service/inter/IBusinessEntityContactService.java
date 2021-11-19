package com.icesi.edu.Stiven.service.inter;

import java.util.Optional;

import com.icesi.edu.Stiven.model.person.Businessentitycontact;

public interface IBusinessEntityContactService {
	
	public Businessentitycontact save(Businessentitycontact bea, Integer a, Integer at, Integer be);
		
	public Optional<Businessentitycontact> findById(Integer id);
	
	public Iterable<Businessentitycontact> findAll();

	public boolean existsById(Integer id);	
	
	public void deletebyId(Integer id);

	//public void editBusinessentityaddress(Integer id);
	
}
