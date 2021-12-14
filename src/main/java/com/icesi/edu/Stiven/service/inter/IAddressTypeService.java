package com.icesi.edu.Stiven.service.inter;

import com.icesi.edu.Stiven.model.person.Addresstype;


public interface IAddressTypeService {

	public <S extends Addresstype> S save(S address);
		
	public Addresstype findbyId(Integer id);
	
	public Iterable<Addresstype> findAll();
		
	public void deletebyId(Integer id);

}
