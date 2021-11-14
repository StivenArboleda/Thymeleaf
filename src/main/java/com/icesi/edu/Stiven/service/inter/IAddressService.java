package com.icesi.edu.Stiven.service.inter;

import java.sql.Timestamp;
import java.util.Optional;

import com.icesi.edu.Stiven.model.person.Address;

public interface IAddressService {

	public <S extends Address> S save(S address);
	
	public <S extends Address> Iterable<S> saveAll(Iterable<S> a);
	
	public Optional<Address> findbyId(Integer id);
	
	public boolean existsById(Integer id);
	
	public void deletebyId(Integer id);
	
	public void editAddress(Integer addressid, String addressline1, String addressline2, String city, Timestamp modifieddate, String postalcode, 
			Integer rowguid, String spatiallocation);
}
