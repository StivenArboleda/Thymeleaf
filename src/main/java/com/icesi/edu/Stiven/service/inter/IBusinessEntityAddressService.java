package com.icesi.edu.Stiven.service.inter;

import java.util.Optional;

import com.icesi.edu.Stiven.model.person.Businessentityaddress;

public interface IBusinessEntityAddressService {
	
	public Businessentityaddress save(Businessentityaddress bea, Integer a, Integer at, Integer be);
		
	public Businessentityaddress findById(Integer id);
	
	public Iterable<Businessentityaddress> findAll();
	
	public void deletebyId(Integer id);

	//public void editBusinessentityaddress(Integer id);
	
}
