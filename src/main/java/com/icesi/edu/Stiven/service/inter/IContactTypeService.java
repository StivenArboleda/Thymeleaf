package com.icesi.edu.Stiven.service.inter;

import com.icesi.edu.Stiven.model.person.Contacttype;


public interface IContactTypeService {

	public <S extends Contacttype> S save(S contact);
	
	public <S extends Contacttype> Iterable<S> saveAll(Iterable<S> a);
	
	public Contacttype findbyId(Integer id);
	
	public Iterable<Contacttype> findAll();
	
	public boolean existsById(Integer id);
	
	public void deletebyId(Integer id);

}
