package com.icesi.edu.Stiven.service.inter;

import com.icesi.edu.Stiven.model.person.Addresstype;


public interface IAddressTypeService {

	public <S extends Addresstype> S save(S address);
	
	public <S extends Addresstype> Iterable<S> saveAll(Iterable<S> a);
	
	public Addresstype findbyId(Integer id);
	
	public Iterable<Addresstype> findAll();
	
	public boolean existsById(Integer id);
	
	public void deletebyId(Integer id);

}
