package com.icesi.edu.Stiven.service.inter;

import java.sql.Timestamp;
import java.util.Optional;

import com.icesi.edu.Stiven.model.person.Address;
import com.icesi.edu.Stiven.model.person.Stateprovince;


public interface IAddressService {

	public <S extends Address> S save(S address);
	
	public <S extends Address> Iterable<S> saveAll(Iterable<S> a);
	
	public Address findbyId(Integer id);
	
	public Iterable<Address> findAll();
	
	public boolean existsById(Integer id);
	
	public void deletebyId(Integer id);
	
	public void editAddress(Integer addressid, String addressline1, String addressline2, String city, String postalcode, Stateprovince sp); 
}
